from cor_rgb_39205 import CorRGB
from ponto_39205 import Ponto3D
from imagem_39205 import Imagem
from vector_39205 import Vector3D
from luz_39205 import Luz
from colorsys import hsv_to_rgb

class CorPhong:

    def __init__(self, k_ambiente, k_difusa, k_especular, brilho):

        self.k_especular = k_especular
        self.k_difusa = k_difusa
        self.k_ambiente = k_ambiente
        self.brilho = brilho

    def __str__(self):

        return("CorPhong(" + str(self.k_ambiente) \
            + ", " + str(self.k_difusa) \
            + ", " + str(self.k_especular) \
            + ", " + str(self.brilho) + ")")

    def get_cor_rgb(self, luz, direcao_luz, normal, direcao_olho, sombra):

        cor_ambiente = self.k_ambiente * luz.intensidade_ambiente

        if sombra == True:
            return(cor_ambiente)
        
        l_interno_n = direcao_luz.interno(normal)
        
        if l_interno_n < 0.0:
            return(cor_ambiente)
        
        cor_difusa = self.k_difusa * luz.intensidade_difusa * l_interno_n

        r = direcao_luz * (-1.0)   + normal * (2.0*l_interno_n)

        cor_especular = self.k_especular * luz.intensidade_especular * ((direcao_olho.interno(r))**self.brilho)

        resultado = cor_ambiente + cor_difusa + cor_especular

        return(resultado)


# testes
if __name__ == "__main__":

    # teste ao construtor
    material_k_ambiente = CorRGB(0.0, 0.0, 0.1)
    material_k_difusa = CorRGB(0.0, 0.0, 0.9)
    material_k_especular = CorRGB(1.0, 1.0, 1.0)
    material_brilho = 100.0
    material_cor = CorPhong(material_k_ambiente,
                            material_k_difusa,
                            material_k_especular,
                            material_brilho)

    # teste a __str__
    print(material_cor)

    # teste a get_cor_rgb
    luz_posicao = Ponto3D(1.0, 0.0, 1.0)
    luz_intensidade_ambiente = CorRGB(1.0, 1.0, 1.0)
    luz_intensidade_difusa = CorRGB(1.0, 1.0, 1.0)
    luz_intensidade_especular = CorRGB(1.0, 1.0, 1.0)
    luz = Luz(luz_posicao, luz_intensidade_ambiente, luz_intensidade_difusa,
                luz_intensidade_especular)
    olho = Ponto3D(-1.0, 0.0, 1.0)
    n_pontos= 100
    imagem = Imagem(100, 100)
    incremento = 0.02 # 2.0/100.0
    normal = Vector3D(0.0, 0.0, 1.0)
    sombra = False
    
    for m in range(100): # indice de linhas
        for n in range(100): # indice de colunas
            ponto = Ponto3D(-1.0 + n*incremento, 1.0 - m*incremento, 0)
            direcao_luz = (luz.posicao - ponto).versor()
            direcao_olho = (olho - ponto).versor()
            cor = material_cor.get_cor_rgb(luz, direcao_luz, normal, direcao_olho, sombra)
            imagem.set_cor(m+1, n+1, cor)

    imagem.guardar_como_ppm("cor_phong.ppm")

    # teste adicional - parametros
    h    = 60.0
    n_pontos = 120

    # teste adicional
    luz_posicao    = Ponto3D(1.0, 0.0, 1.0)
    luz_i_ambiente = CorRGB(1.0, 1.0, 1.0)
    luz_i_difusa    = CorRGB(1.0, 1.0, 1.0)
    luz_i_especular = CorRGB(1.0, 1.0, 1.0)
    luz = Luz(luz_posicao, luz_i_ambiente, luz_i_difusa, luz_i_especular)
    olho = Ponto3D(-1.0, 0.0, 1.0)
    k_ambiente = CorRGB(0.0, 0.0, 0.0)
    k_difusa    = CorRGB(0.0, 0.0, 0.0)
    k_especular = CorRGB(0.9, 0.9, 0.9)
    brilho    = 100.0
    k_ambiente.set_hsv(h, 1.0, 0.1)
    k_difusa.set_hsv(h, 1.0, 0.8)
    cor_phong = CorPhong(k_ambiente,
                        k_difusa,
                        k_especular,
                        brilho)
    imagem    = Imagem(n_pontos, n_pontos)
    incremento = 2.0 / n_pontos
    normal    = Vector3D(0.0, 0.0, 1.0)
    sombra    = False

    for m in range(n_pontos): # indice de linhas
        for n in range(n_pontos): # indice de colunas
            ponto = Ponto3D(-1.0 + n*incremento,
                            1.0 - m*incremento, 0)
            direcao_luz = (luz.posicao - ponto).versor()
            direcao_olho = (olho - ponto).versor()
            cor = cor_phong.get_cor_rgb(luz, direcao_luz, normal,
                                        direcao_olho, sombra)

            imagem.set_cor(m+1, n+1, cor)

    imagem.guardar_como_ppm("cor_phong_adicional.ppm")

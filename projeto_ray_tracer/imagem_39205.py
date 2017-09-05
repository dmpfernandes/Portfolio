from io import StringIO
from cor_rgb_39205 import CorRGB

class Imagem:
    
    def __init__(self, numero_linhas, numero_colunas):
    #constructor, define os atributos
    #Define a imagem com o numero de linhas e o numero de colunas

        self.numero_linhas  = numero_linhas #atributo de um objecto que tem o
        #mesmo nome da variavel local
        self.numero_colunas = numero_colunas

        self.linhas = []
        #cria linhas com pixeis
        for l in range(numero_linhas):
            linha = []
            for c in range(numero_colunas):
                pixel = CorRGB(0.0,0.0,0.0)
                linha.append(pixel)
            self.linhas.append(linha)

    def __str__(self):
    #este metodo tem de retornar uma string

        string_dinamica = StringIO()

        string_dinamica.write("P3\n")
        string_dinamica.write(str(self.numero_colunas) + " " +
                              str(self.numero_linhas) + "\n") #tamanho da imagem, numero de linhas e numero de colunas
        string_dinamica.write("255\n")
        for l in range(self.numero_linhas):
            for c in range(self.numero_colunas):
                string_dinamica.write(str(self.linhas[l][c]) + " ")

            string_dinamica.write("\n")  

        resultado = string_dinamica.getvalue()

        string_dinamica.close()
        
        return(resultado)

    def guardar_como_ppm(self, nome_ficheiro):

        file = open(nome_ficheiro, "w")
        file.write(str(self))
        file.close()

    def set_cor(self, linha, coluna, cor):

        self.linhas[linha-1][coluna-1] = cor

    def get_cor(self, linha, coluna):

        return(self.linhas[linha-1][coluna-1])
    
    
        
# testes
if __name__ == "__main__":
    
    # teste ao construtor
    imagem1 = Imagem(5, 5)
    
    # teste a __str__
    imagem2 = Imagem(5, 5)
    print(imagem2)
    print(" ")
    
    # teste a set_cor
    imagem3 = Imagem(5, 5)
    branco = CorRGB(1.0, 1.0, 1.0)
    imagem3.set_cor(3, 3, branco)
    print(imagem3)
    print(" ")
    
    # testes a get_cor
    imagem4 = Imagem(5, 5)
    branco = CorRGB(1.0, 1.0, 1.0)
    imagem4.set_cor(3, 3, branco)
    print(imagem4.get_cor(3, 3))
    print(imagem4.get_cor(5, 5))
    print(" ")
    
    # teste a guardar_como_ppm
    imagem5 = Imagem(3, 5)
    red = CorRGB(1.0, 0.0, 0.0)
    green = CorRGB(0.0, 1.0, 0.0)
    blue = CorRGB(0.0, 0.0, 1.0)
    imagem5.set_cor(2, 2, red)
    imagem5.set_cor(2, 3, green)
    imagem5.set_cor(2, 4, blue)
    imagem5.guardar_como_ppm("imagem5.ppm")
    print(imagem5)
    print(" ")
    
    # teste adicional
    linhas = 100
    colunas = 200
    imagem6 = Imagem(linhas, colunas)
    h = 130.0
    incremento_s = 1.0 / (colunas - 1)
    incremento_v = 1.0 / (linhas - 1)
    for l in range(linhas):
        v = l * incremento_v
        for c in range(colunas):
            s = c * incremento_s
            pixel = CorRGB(0.0, 0.0, 0.0)
            pixel.set_hsv(h, s, v)
            imagem6.set_cor(l+1, c+1, pixel)
    imagem6.guardar_como_ppm("imagem6.ppm")


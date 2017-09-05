from ponto_39205 import Ponto3D
from cor_rgb_39205 import CorRGB
from cor_phong_39205 import CorPhong
from face_39205 import FaceTriangular
from luz_39205 import Luz
from vector_39205 import Vector3D
from camara_39205 import Camara
from recta_39205 import Recta
from imagem_39205 import Imagem
#from cenario import Cenario


class RayTracer:

    def __init__(self, lista_faces, lista_luzes, camara, cor_fundo):
        #self.cenario = cenario
        self.lista_faces = lista_faces
        self.lista_luzes = lista_luzes
        self.camara = camara
        self.cor_fundo = cor_fundo

    def __str__(self):        
        return("RayTracer("+ str(face) + "\n, \n"
               + str(luz) + "\n, \n" \
               + str(camara)+ "\n, \n"
               + str(cor_fundo) + "\n)")
   
    
    def get_cor_face(self, face, ponto_intercecao, direcao_olho):

        soma_cores = CorRGB(0.0, 0.0, 0.0)
        for r in range(len(lista_luzes)):
            luz_momentanea = lista_luzes[r] #luz
            recta1 = Recta(ponto_intercecao, luz_momentanea.posicao)
            direcao_luz = (luz_momentanea.posicao - ponto_intercecao).versor()

            #os pontos
            ponto1 = face.ponto1
            ponto2 = face.ponto2
            ponto3 = face.ponto3

            #os vectores            
            vector1 = ponto2 - ponto1
            vector2 = ponto3 - ponto1

            #o vector normal
            normal = vector1.externo(vector2).versor()

            interseccao_face = ray_tracer.get_face_intercetada_mais_proxima(recta1)[0]
            
            # Analisa se a recta interseta alguma face
            # se não intersetar vai obter a cor Phong
        
            if (interseccao_face == False):
                sombra = False
                cor = face.cor_phong.get_cor_rgb(luz_momentanea, direcao_luz,
                                                 normal, direcao_olho, sombra)
                soma_cores = soma_cores.soma(cor)
            # caso intersete obter a cor Phong em sombra
                
            else:
                sombra = True
                cor = face.cor_phong.get_cor_rgb(luz_momentanea, direcao_luz,
                                                 normal,  direcao_olho, sombra)

                soma_cores = soma_cores.soma(cor)
               
        return (soma_cores)

    

    def get_face_intercetada_mais_proxima(self, raio):

        # Lista para valores de t e lista as faces intersetadas
        interseccao = True
        valores_t = []
        faces = []
        
        # ve a lista de faces para encontrar a intersecao com cada face
        
        for b in range(len(lista_faces)):
            face_momentanea = lista_faces[b]
            aux = face_momentanea.interceta_triangulo(raio)
            interseccao = aux[0]
            ponto_interseccao = aux[1]
            t_momentaneo = aux[2]
            
            
            # caso a recta raio intersete alguma das faces da cena preenche
            # as listas com a face e o t
            if(interseccao == True):
                if(t_momentaneo != None):
                    valores_t.append(t_momentaneo)
                    faces.append(face_momentanea)
                    
            
        if (len(valores_t) != 0):
            for x in range(len(valores_t)):
                if(valores_t[x] != None):
                    aux = valores_t.sort()
                    t_minimo = valores_t[0]
                    
        


        
        if (len(faces) != 0):   
            for b in range(len(faces)):
                face_momentanea = faces[b]
                caracteristicas = face_momentanea.interceta_triangulo(raio)
                ponto_interseccao = caracteristicas[1]
                t_momentaneo = caracteristicas[2]

                # caso o parametro t seja igual ao t minimo retorna uma lista
                # com todos os valores
                if (t_minimo == t_momentaneo):
                    interseccao = True
                    ponto_intercecao = ponto_interseccao
                    t = t_momentaneo
                    face = face_momentanea

        # se não interceta nenhuma face retorna [False, None, None, None]

        else:
            interseccao = False
            ponto_intercecao = None
            t = None
            face = None

        return ([interseccao, ponto_intercecao, t, face])
    


    def get_cor_vista_por_raio(self, raio):

        # obter a face intersetada mais proxima e o ponto de intersecao
        
        face_intersetada = ray_tracer.get_face_intercetada_mais_proxima(raio)
        interseta = face_intersetada[0]
        ponto_intercecao = face_intersetada[1]
        face = face_intersetada[3]
        
 
        # caso nao exista a face retorna a cor de fundo
        
        if (interseta == False):
            cor_face = cor_fundo

        # caso exista obtem a direcçao do olho e a cor da face retornando
        # essa mesma
        
        else:
            direcao_olho = (camara.posicao - ponto_intercecao).versor()
            cor_face = ray_tracer.get_cor_face(face, ponto_intercecao, direcao_olho)
        
        return(cor_face)


    def renderiza(self):

        # criação da imagem com numero de linhas e colunas igual à resoluçao
        # vertical e horizontal da camara
        
        linhas = camara.resolucao_vertical
        colunas = camara.resolucao_horizontal

        imagem = Imagem(linhas, colunas)

        for l in range(linhas):
            # imprime o numero da linha corrente

            print ("linha = " + str(l+1) + " de " + str(linhas))          
            

            # para cada coluna de cada linha obtem a cor
            # vista pelo raio de visão definido pela recta criada
            for c in range(colunas):
                posicao = camara.posicao
                ponto = camara.get_pixel_global(l, c)
                recta = Recta(posicao, ponto)
                cor = ray_tracer.get_cor_vista_por_raio(recta)
                imagem.set_cor(l, c, cor)

        return (imagem)



# testes
if __name__ == "__main__":
    # teste ao construtor
    # teste ao construtor - cor da face
    verde = CorRGB(0.0, 0.3, 0.0)
    brilho = 100.0
    cor = CorPhong(verde, verde, verde, brilho)

    # teste ao construtor - face
    p1 = Ponto3D(0.0, 0.0, 0.0)
    p2 = Ponto3D(1.0, 0.0, 0.0)
    p3 = Ponto3D(0.0, 1.0, 0.0)
    face = FaceTriangular(p1, p2, p3, cor)
    lista_faces = [face]

    # teste ao construtor - luz
    branco = CorRGB(1.0, 1.0, 1.0)
    luz_posicao = Ponto3D(1.0, 0.0, 2.0)
    luz = Luz(luz_posicao, branco, branco, branco)
    lista_luzes = [luz]

    # teste ao construtor - camara
    camara_posicao = Ponto3D(0.0, 0.0, 2.0)
    olhar_para = Ponto3D(0.0, 0.0, 0.0)
    vertical = Vector3D(0.0, 1.0, 0.0)
    distancia_olho_plano_projecao = 1.5
    largura_retangulo_projecao = 2.0
    altura_retangulo_projecao = 2.0
    resolucao_horizontal = 50
    resolucao_vertical = 50
    camara = Camara(camara_posicao, olhar_para, vertical, distancia_olho_plano_projecao, largura_retangulo_projecao, altura_retangulo_projecao, resolucao_horizontal, resolucao_vertical)

    # teste ao construtor - cor de fundo
    cor_fundo = CorRGB(0.0, 0.0, 0.2)
    
    # teste ao construtor - ray tracer
    ray_tracer = RayTracer(lista_faces, lista_luzes, camara, cor_fundo)

    # teste a __str__
    print(ray_tracer)
    print(" ")
    
    # teste a renderiza
    imagem = ray_tracer.renderiza()
    imagem.guardar_como_ppm("teste1.ppm")
    
    #camara
    posicao = Ponto3D(0.0, 0.0, 2.5)
    olhar_para = Ponto3D(0.0, 0.0, 0.0)
    vertical = Vector3D(0.0, 1.0, 0.0)
    distancia_olho_plano_projecao = 0.5
    largura_retangulo_projecao = 8.0
    altura_retangulo_projecao = 4.0
    resolucao_horizontal = 600
    resolucao_vertical = 300
    camara = Camara(posicao, olhar_para, vertical, distancia_olho_plano_projecao,
                    largura_retangulo_projecao, altura_retangulo_projecao,
                    resolucao_horizontal, resolucao_vertical)
    #triangulos cinzentos
    a1 = Ponto3D(16.0, -5.1, 0.0)
    b1 = Ponto3D(150.0, -5.1, -20.0)
    c1 = Ponto3D(-16.0, -5.1, 0.0)
    a2 = Ponto3D(-16.0, -5.1, 0.0)
    b2 = Ponto3D(150.0, -5.1, -20.0)
    c2 = Ponto3D(-16.0, 5.1, 0.0)
    k_ambiente = CorRGB(0.5, 0.5, 0.5)
    k_difusa = CorRGB(0.75, 0.75, 0.75)
    k_especular = CorRGB(0.0, 0.0, 0.0)
    brilho = 1.0
    cor = CorPhong(k_ambiente, k_difusa, k_especular, brilho)
    face1 = FaceTriangular(a1, b1, c1, cor) 
    face2 = FaceTriangular(a2, b2, c2, cor)
    print("brilho triangulos cinzentos ")
    print(brilho)
    print(" ")

    #triangulos vermelhos
    #letra M
    a3 = Ponto3D(-16.0, -5.0, 0.0)
    b3 = Ponto3D(-12.0, -5.0, 0.0)
    c3 = Ponto3D(-16.0, 5.0, 0.0)
    a4 = Ponto3D(-16.0, 5.0, 0.0)
    b4 = Ponto3D(-11.0, 0.0, 0.0)
    c4 = Ponto3D(-6.0, 5.0, 0.0)
    a5 = Ponto3D(-10.0, -5.0, 0.0)
    b5 = Ponto3D(-6.0, -5.0, 0.0)
    c5 = Ponto3D(-6.0, 5.0, 0.0)
    #letra C
    a6 = Ponto3D(-5.0, -2.0, 0.0)
    b6 = Ponto3D(5.0, 5.0, 0.0)
    c6 = Ponto3D(-5.0, 3.0, 0.0)
    a7 = Ponto3D(-5.0, -3.0, 0.0)
    b7 = Ponto3D(5.0, -5.0, 0.0)
    c7 = Ponto3D(-5.0, 2.0, 0.0)
    #letra G
    a8 = Ponto3D(6.0, -2.0, 0.0)
    b8 = Ponto3D(16.0, 5.0, 0.0)
    c8 = Ponto3D(6.0, 5.0, 0.0)
    a9 = Ponto3D(6.0, -5.0, 0.0)
    b9 = Ponto3D(16.0, -5.0, 0.0)
    c9 = Ponto3D(6.0, 2.0, 0.0)
    a10 = Ponto3D(16.0, -5.0, 0.0)
    b10 = Ponto3D(16.0, 0.0, 0.0)
    c10 = Ponto3D(12.0, 0.0, 0.0)
    k_ambiente = CorRGB(1.0, 0.0, 0.0)
    k_difusa = CorRGB(1.0, 0.0, 0.0)
    k_especular = CorRGB(1.0, 0.0, 0.0)
    brilho = 1000.0
    cor = CorPhong(k_ambiente, k_difusa, k_especular, brilho)
    print("brilho triangulos vermelhos ")
    print(brilho)
    print(" ")
    #triangulos da letra M
    face3 = FaceTriangular(a3, b3, c3, cor) 
    face4 = FaceTriangular(a4, b4, c4, cor)
    face5 = FaceTriangular(a5, b5, c5, cor)
    #triangulos da letra C
    face6 = FaceTriangular(a6, b6, c6, cor) 
    face7 = FaceTriangular(a7, b7, c7, cor)
    #triangulos da letra G
    face8 = FaceTriangular(a8, b8, c8, cor) 
    face9 = FaceTriangular(a9, b9, c9, cor)
    face10 = FaceTriangular(a10, b10, c10, cor)
    
    #cor do fundo
    cor_fundo = CorRGB(0.0, 0.0, 0.0)
    
    #lista de luzes
    posicaoluz1 = Ponto3D(16.0,2.0,2.0)
    posicaoluz2 = Ponto3D(-16.0,-2.0,2.0)
    i_ambiente = CorRGB(0.1,0.1,0.1) 
    i_difusa = CorRGB(0.75,0.75,0.75)
    i_especular = CorRGB(0.75,0.75,0.75)
    luz1 = Luz(posicaoluz1, i_ambiente, i_difusa, i_especular)
    luz2 = Luz(posicaoluz2, i_ambiente, i_difusa, i_especular)

    lista_faces = [face1, face2, face3, face4, face5, face6, face7, face8, face9, face10]
    lista_luzes = [luz1, luz2]
    
    # ray tracer
    ray_tracer = RayTracer(lista_faces, lista_luzes, camara, cor_fundo)
    # renderização
    imagem = ray_tracer.renderiza()
    # ficheiro com a renderização
    imagem.guardar_como_ppm("teste2.ppm")
    

    

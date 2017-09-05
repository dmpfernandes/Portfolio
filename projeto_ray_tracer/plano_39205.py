from ponto_39205 import Ponto3D
from vector_39205 import Vector3D
from matriz_39205 import Matriz
from recta_39205 import Recta

TOLERANCIA_ZERO = 10.0**(-10)

class ErroPontosColineares(Exception):

    """Exceção lançada quando se tenta definir um plano com
    pontos colineares ou coincidentes."""
    pass

class Plano:
    """Classe que representa um plano."""

    def __init__(self, ponto1, ponto2, ponto3):
        """Cria um plano definido por três pontos."""

        self.ponto1 = ponto1
        self.ponto2 = ponto2
        self.ponto3 = ponto3
        
        v12 = ponto2 - ponto1
        v13 = ponto3 - ponto1
        
        ext = v12.externo(v13)
        
        if ext.comprimento() < TOLERANCIA_ZERO:

            raise(ErroPontosColineares)

        self.normal = ext.versor()
        
    def __str__(self):
        """Retorna uma string que representa o plano."""

        return("Plano(" + \
            str(self.ponto1) + ", " + \
            str(self.ponto2) + ", " + \
            str(self.ponto3) + ", " + \
            str(self.normal) + \
                ")")

    def interceta_triangulo(self, raio):

        triangulo_intersecçao = True

        xa = self.ponto1.x
        ya = self.ponto1.y
        za = self.ponto1.z

        xb = self.ponto2.x
        yb = self.ponto2.y
        zb = self.ponto2.z

        xc = self.ponto3.x
        yc = self.ponto3.y
        zc = self.ponto3.z

        xe = raio.origem.x
        ye = raio.origem.y
        ze = raio.origem.z

        xd = raio.vector_diretor.x 
        yd = raio.vector_diretor.y
        zd = raio.vector_diretor.z

        # Cramer

        A = Matriz(3, 3)
        A.set_linha (1, [xa-xb, xa-xc, xd]),
        A.set_linha (2, [ya-yb, ya-yc, yd]),
        A.set_linha (3, [za-zb, za-zc, zd])   
        detA = A.det_3x3()

        if abs(detA) < TOLERANCIA_ZERO:
            triangulo_interseccao = False
            ponto_interseccao = None
            t = None
            return ([triangulo_interseccao, ponto_interseccao, t])


        
        # parâmetro t

        tNum = Matriz(3, 3)
        tNum.set_linha(1, [xa-xb, xa-xc, xa-xe]),
        tNum.set_linha(2,  [ya-yb, ya-yc, ya-ye]),
        tNum.set_linha (3,[za-zb, za-zc, za-ze]),
        t = tNum.det_3x3() / detA

        if t < TOLERANCIA_ZERO:
           triangulo_interseccao = False
           ponto_interseccao = None
           t = None

        # coordenada gama
        
        gamaNum = Matriz(3, 3)
        gamaNum.set_linha(1, [xa-xb, xa-xe, xd]),
        gamaNum.set_linha (2,[ya-yb, ya-ye, yd]),
        gamaNum.set_linha (3,[za-zb, za-ze, zd]),
        gama = gamaNum.det_3x3() / detA

        if (gama < 0.0 or gama > 1.0):
            triangulo_interseccao = False
            ponto_interseccao = None
            t = None

        # coordenada beta

        betaNum = Matriz(3, 3)
        betaNum.set_linha(1, [xa-xe, xa-xc, xd]), 
        betaNum.set_linha (2,[ya-ye, ya-yc, yd]),
        betaNum.set_linha (3,[za-ze, za-zc, zd])

        beta = betaNum.det_3x3() / detA
        
        if (beta < 0.0 or beta > 1.0): ## eu nao pus menos gama
            triangulo_interseccao = False
            ponto_interseccao = None
            t = None


        Alpha =1-beta-gama
        if (Alpha < 0.0 or Alpha > 1.0): ## eu nao pus menos gama
            triangulo_interseccao = False
            ponto_interseccao = None
            t = None

        

        # ponto a = ponto 1, ponto b = ponto 2, ponto c = ponto 3
        #
        # P = a + beta(b-a) + gama(c-a)
        vab = self.ponto2 - self.ponto1
        vac = self.ponto3 - self.ponto1

        pontoIntercecao = (self.ponto1 + vab*beta) + vac*gama

        return(True, pontoIntercecao, t)
        


        

#testes
if __name__ == "__main__":

    # teste ao construtor
    a = Ponto3D(0.0, 0.0, 0.0)
    b = Ponto3D(2.0, 0.0, 0.0)
    c = Ponto3D(0.0, 2.0, 0.0)
    plano1 = Plano(a, b, c)
    print("Até aqui não foram lançadas exceções.")
    # teste a TOLERANCIA_ZERO
    print("TOLERANCIA_ZERO = " + str(TOLERANCIA_ZERO))
    # teste à exceção ErroPontosColineares
    try:
        plano2 = Plano(a, b, b)
    except ErroPontosColineares:
        print("Ao tentar definir-se o plano plano2 = Plano(a, b, b)")
        print("foi lançada a exceção ErroPontosColineares.")
        print("A execução foi interrompida. plano2 não ficou definida.")
        print(" ")
    # teste a __str__
    # a normal tem que apontar no sentido do eixo dos z’s
    # e tem que ter comprimento 1
    print(plano1)
    print(" ")

    # testes a interceta_triangulo
    p1 = Ponto3D(1.0, 1.0, 10.0)
    p2 = Ponto3D(1.0, 1.0, 5.0)
    r1 = Recta(p1, p2)
    trio = plano1.interceta_triangulo(r1)
    if trio[0] == True:
        print("r1 interceta plano1.")
        print("interceção = " + str(trio[1]))
        print("parâmetro t = " + str(trio[2]))
        print("interceção calculada com a equação da reta e t.")
        print("(tem que dar o mesmo que trio[1])")
        t = trio[2]
        pi = r1.origem + (r1.vector_diretor * t)
        print(pi)
    else:
        print("r1 NÃO interceta plano1.")
    p3 = Ponto3D(2.0, 2.0, 10.0)
    r2 = Recta(p1, p3)
    trio = plano1.interceta_triangulo(r2)
    if trio[0] == True:
        print("r2 interceta plano1.")
        print("interceção = " + str(trio[1]))
        print("parâmetro t = " + str(trio[2]))
    else:
        print("r2 NÃO interceta plano1.")


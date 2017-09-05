from vector_39205 import Vector3D

class Ponto3D:

    def __init__(self, x, y, z):
        self.x = x
        self.y = y
        self.z = z
        
    def get_x(self):
        return(self.x)

    def get_y(self):
        return(self.y)

    def get_z(self):
        return(self.z)

    def __str__(self):
        return("Ponto3D(" + str(self.x) + "," + str(self.y) + "," + str(self.z)
                + ")")
    
    def adiciona_vector(self, um_vector):
        av_x = (self.x) + (um_vector.x)
        av_y = (self.y) + (um_vector.y)
        av_z = (self.z) + (um_vector.z)
        av_resultado = Ponto3D(av_x, av_y, av_z)
        return av_resultado

    def __add__(self, um_vector):
        return(self.adiciona_vector(um_vector))
    
    def subtrai_ponto(self, ponto_inicial):
        return(Vector3D(self.x - ponto_inicial.x,
                        self.y - ponto_inicial.y,
                         self.z - ponto_inicial.z))

    def __sub__(self, ponto_inicial):
        return(self.subtrai_ponto(ponto_inicial))

# testes
if __name__ == "__main__":

    # teste ao construtor
    p1 = Ponto3D(1.0, 2.0, 3.0)

    # teste a get_x
    print("coordenada x de p1 = ")
    print(p1.get_x())
    print(" ")

    # teste a get_y
    print("coordenada y de p1 = ")
    print(p1.get_y())
    print(" ")

    # teste a get_z
    print("coordenada z de p1 = ")
    print(p1.get_z())
    print(" ")

    # teste a __str__
    print("p1 = ")
    print(p1)
    print(" ")

    # teste a adiciona_vetor
    v1 = Vector3D(10.0, 20.0, 30.0)
    p2 = p1.adiciona_vector(v1)
    print("v1 = ")
    print(v1)
    print("p2 = ")
    print(p2)
    print(" ")

    # teste a +
    p3 = p1 + v1
    print("p3 = p1 + v1 = ")
    print(p3)
    print(" ")

    # teste a subtrai_ponto
    v2 = p2.subtrai_ponto(p1)
    print("v2 = ")
    print(v2)
    print(" ")

    # teste a -
    v3 = p2 - p1
    print("v3 = ")
    print(v3)
    print(" ")


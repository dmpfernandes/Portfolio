from math import sqrt
class Vector3D: 
    def __init__(self, x , y, z):  
        self.x = x
        self.y = y
        self.z = z
        
    def get_x(self): 
        return (self.x)
    
    def get_y(self): 
        return (self.y)
        
    def get_z(self):
        return (self.z)
        
    def __str__ (self): 
        return("Vector3D(" + str(self.x) + ", " + str(self.y) + ", " \
        + str(self.z) + ")" )
        
    def adiciona(self, outro_vector):
        return(Vector3D(self.x + outro_vector.x,
                       self.y + outro_vector.y,
                       self.z + outro_vector.z))
                    
    def __add__(self,outro_vector): 
        return(self.adiciona(outro_vector))
        
    def multiplica_escalar(self, escalar):
        return(Vector3D(self.x * escalar, self.y * escalar,
                       self.z * escalar))

    def __mul__(self, escalar):
        return(self.multiplica_escalar(escalar))

    def comprimento(self):
        return(sqrt(self.x**2 + self.y**2 + self.z**2))

    def versor(self):
        fator = 1.0/self.comprimento()
        return(self * fator)
        
    def interno(self, outro_vector):       
        produto_interno = (((self.x) * (outro_vector.x)) +((self.y) * (outro_vector.y)) +((self.z) * (outro_vector.z)))                  
        return produto_interno
        
    def externo(self, outro_vector):
        externo_x = ((self.y)*(outro_vector.z)) - ((self.z)*(outro_vector.y)) 
        externo_y = ((self.z)*(outro_vector.x)) - ((self.x)*(outro_vector.z))
        externo_z = ((self.x)*(outro_vector.y)) - ((self.y)*(outro_vector.x))
        vector_externo = Vector3D(externo_x, externo_y, externo_z)
        return vector_externo
        
        
        
# testes
if __name__ == "__main__":
# teste ao construtor
    v1 = Vector3D(1.0, 2.0, 3.0)
    
    # teste a get_x
    print("coordenada x de v1 = ")
    print(v1.get_x())
    print(" ")
    
    # teste a get_y
    print("coordenada y de v1 = ")
    print(v1.get_y())
    print(" ")

    # teste a get_z
    print("coordenada z de v1 = ")
    print(v1.get_z())
    print(" ")

    # teste a __str__
    print("v1 = ")
    print(v1)
    print(" ")

    # teste a adiciona
    v2 = Vector3D(10.0, 20.0, 30.0)
    v3 = v1.adiciona(v2)
    print("v1 = ")
    print(v1)
    print("v2 = ")
    print(v2)
    print("v3 = ")
    print(v3)
    print(" ")
    
    # teste a +
    v4 = v1 + v2
    print("v4 = ")
    print(v4)
    print(" ")

    # teste a multiplica_escalar
    a = 2.0
    v5 = v1.multiplica_escalar(a)
    print("v5 = ")
    print(v5)
    print(" ")

    # teste a *
    v6 = v1 * a
    print("v6 = ")
    print(v6)
    print(" ")

    # teste a comprimento
    v7 = Vector3D(3.0, 0.0, 4.0)
    cv7 = v7.comprimento()
    print("v7 = ")
    print(v7)
    print("comprimento de v7 = ")
    print(cv7)
    print(" ")

    # teste a versor
    vv7 = v7.versor()
    cvv7 = vv7.comprimento()
    print("vv7 = ")
    print(vv7)
    print("comprimento de vv7 = ")
    print(cvv7)
    print(" ")

    # teste a interno
    print("v1 =")
    print(v1)
    print("v7 =")
    print(v7)
    iv1v7 = v1.interno(v7)
    print("v1 interno v7 = ")
    print(iv1v7)
    print(" ")

    # teste a externo
    e = v1.externo(v7)
    print("e = v1 externo v7 = ")
    print(e)
    print("v1 interno e = ")
    print(v1.interno(e))
    print("v7 interno e = ")
    print(v7.interno(e))
    print(" ")
                
            
            
     

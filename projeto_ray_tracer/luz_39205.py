from ponto_39205 import Ponto3D
from cor_rgb_39205 import CorRGB

class Luz:

    def __init__(self, posicao, intensidade_ambiente, intensidade_difusa,
    intensidade_especular):
        self.posicao = posicao
        self.intensidade_ambiente = intensidade_ambiente
        self.intensidade_difusa = intensidade_difusa
        self.intensidade_especular = intensidade_especular

    def __str__(self):
        return("Luz(" + str(self.posicao) + ", " \
                + str(self.intensidade_ambiente) + ", " \
                + str(self.intensidade_difusa)
                + ", " \
                + str(self.intensidade_especular) + ")")

    def get_posicao(self):
        return (self.posicao)

    def get_intensidade_ambiente(self):
        return (self.intensidade_ambiente)

    def get_intensidade_difusa(self):
        return (self.intensidade_difusa)

    def get_intensidade_especular(self):
        return (self.intensidade_especular)
    
# testes
if __name__ == "__main__":

    # teste ao construtor
    posicao = Ponto3D(1.0, 2.0, 3.0)
    i_ambiente = CorRGB(0.1, 0.2, 0.3)
    i_difusa = CorRGB(0.4, 0.5, 0.6)
    i_especular = CorRGB(0.7, 0.8, 0.9)
    luz = Luz(posicao, i_ambiente, i_difusa, i_especular)

    # teste a __str__
    print(luz)
    print(" ")
    
    # teste a get_posicao
    print(luz.get_posicao())
    print(" ")

    # teste a get_intensidade_ambiente
    print(luz.get_intensidade_ambiente())
    print(" ")

    # teste a get_intensidade_difusa
    print(luz.get_intensidade_difusa())
    print(" ")

    
    # teste a get_intensidade_especular
    print(luz.get_intensidade_especular())
    print(" ")








    

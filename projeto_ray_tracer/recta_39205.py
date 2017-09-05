from ponto_39205 import Ponto3D
from vector_39205 import Vector3D

TOLERANCIA_ZERO = 10.0**(-10)

class ErroPontosCoincidentes(Exception):
    """Exceção lançada quando se tenta definir uma recta com dois
    pontos coincidentes.
    """
    
    pass

class Recta:
    """Classe que representa uma recta."""

    def __init__(self, origem, destino):
        """Cria uma recta que passa por dois pontos."""

        self.origem = origem
        self.destino = destino
        direcao = destino - origem

        if direcao.comprimento() < TOLERANCIA_ZERO:
            raise(ErroPontosCoincidentes)

        self.vector_diretor = direcao.versor()
        
    def __str__(self):
        """Retorna uma string que representa a recta."""

        return("Recta(" + str(self.origem) + ", " + str(self.destino) + ", " \
                    + str(self.vector_diretor) + ")")

# testes
if __name__ == "__main__":

    # teste ao construtor
    p1 = Ponto3D(0.0, 0.0, 0.0)
    p2 = Ponto3D(1.0, 2.0, 3.0)
    r1 = Recta(p1, p2)
    print("Até aqui não foram lançadas exceções.")
    # teste à exceção ErroPontosCoincidentes
    try:
        r2 = Recta(p2, p2)
    except ErroPontosCoincidentes:
        print("Ao tentar definir-se a recta r2 = Recta(p2, p2)")
        print("foi lançada a exceção ErroPontosCoincidentes.")
        print("A execução foi interrompida. r2 não ficou definida.")

    # teste a __str__
    print(r1)

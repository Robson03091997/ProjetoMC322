public class Obstaculo {
    private int x, y, z;
    private int x1, y1, z1;
    private TipoObstaculo tipo;
    public static int numeroDeObstaculos = 0;

    Obstaculo(int x, int y, int z, int largura, int comprimento, int altura, TipoObstaculo tipo){
        this.x = x;
        this.y = y;
        this.z = z;
        this.x1 = x + largura;
        this.y1 = y + comprimento;
        this.z1 = z + altura;
        this.tipo = tipo;
        Obstaculo.numeroDeObstaculos++;
    }

    public TipoObstaculo getTipo() {
        return this.tipo;
    }

    public boolean bloqueiaPassagem(int posicaoX, int posicaoY, int posicaoZ){
        if (posicaoX >= this.x && posicaoX <= this.x1){
            if (posicaoY >= this.y && posicaoY <= this.y1){
                if (posicaoZ >= this.z && posicaoZ <= this.z1){
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }


}
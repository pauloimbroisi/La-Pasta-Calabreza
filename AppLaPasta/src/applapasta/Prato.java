package applapasta;

public class Prato {
    
    private String nome;
    private float preco;
    private int numPessoasServidas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getNumPessoasServidas() {
        return numPessoasServidas;
    }

    public void setNumPessoasServidas(int numPessoasServidas) {
        this.numPessoasServidas = numPessoasServidas;
    }
    
    public void mostrarInfo(){
        System.out.println("Nome do prato:" + this.nome +
                "\nPreço: R$" + this.preco + 
                "\nServe " + this.numPessoasServidas + " pessoas.\n");
    }
}

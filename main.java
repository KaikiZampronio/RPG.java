package org.example;


abstract class Personagem {
    protected String nome;
    protected int nivel;
    protected int hp; // Pontos de Vida
    protected int atk; // Pontos de Ataque
    protected int def; // Pontos de Defesa


    public Personagem(String nome, int nivel, int hp, int atk, int def) {
        this.nome = nome;
        this.nivel = nivel;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }


    public void atacar(Personagem inimigo) {
        int dano = calcularDano(this.atk, inimigo.def);
        System.out.println(this.nome + " ataca " + inimigo.nome + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }


    protected int calcularDano(int ataque, int defesa) {
        int dano = ataque - defesa;
        return (dano > 0) ? dano : 0; // Não causar dano negativo
    }


    public void receberDano(int dano) {
        this.hp -= dano;
        if (this.hp < 0) this.hp = 0;
        System.out.println(this.nome + " agora tem " + this.hp + " HP.");
    }


    public void exibirStatus() {
        System.out.println("Nome: " + nome + ", Nível: " + nivel + ", HP: " + hp + ", ATK: " + atk + ", DEF: " + def);
    }


    public abstract void habilidadeEspecial(Personagem inimigo);


    public boolean estaVivo() {
        return this.hp > 0;
    }
}


class Guerreiro extends Personagem {
    private int forcaExtra; // Força extra no ataque
    private int armadura; // Defesa extra

    public Guerreiro(String nome, int nivel) {
        super(nome, nivel, 150, 30, 20);
        this.forcaExtra = 10;
        this.armadura = 5;
        this.def += armadura;
    }

    @Override
    public void atacar(Personagem inimigo) {
        int dano = calcularDano(this.atk + forcaExtra, inimigo.def);
        System.out.println(this.nome + " (Guerreiro) ataca com força extra " + inimigo.nome + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println(this.nome + " usa habilidade especial: Ataque Avassalador!");
        atacar(inimigo);
    }
}


class Mago extends Personagem {
    private int mana; // Pontos de Mana
    private int poderMagico; // Dano mágico

    public Mago(String nome, int nivel) {
        super(nome, nivel, 100, 20, 10);
        this.mana = 50;
        this.poderMagico = 40;
    }


    public void lancarMagia(Personagem inimigo) {
        if (mana >= 10) {
            mana -= 10;
            int dano = poderMagico;
            System.out.println(this.nome + " (Mago) lança magia em " + inimigo.nome + " causando " + dano + " de dano mágico!");
            inimigo.receberDano(dano);
        } else {
            System.out.println(this.nome + " não tem mana suficiente para lançar magia!");
        }
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println(this.nome + " usa habilidade especial: Bola de Fogo!");
        lancarMagia(inimigo);
    }
}


class Arqueiro extends Personagem {
    private int precisao; // Precisão extra no ataque
    private int alcance; // Alcance extra para ataques à distância

    public Arqueiro(String nome, int nivel) {
        super(nome, nivel, 120, 25, 15);
        this.precisao = 15;
        this.alcance = 10;
    }


    public void atirarFlecha(Personagem inimigo) {
        int dano = calcularDano(this.atk + precisao, inimigo.def);
        System.out.println(this.nome + " (Arqueiro) atira uma flecha de longo alcance em " + inimigo.nome + " causando " + dano + " de dano!");
        inimigo.receberDano(dano);
    }

    @Override
    public void habilidadeEspecial(Personagem inimigo) {
        System.out.println(this.nome + " usa habilidade especial: Chuva de Flechas!");
        atirarFlecha(inimigo);
    }
}



package mc322.lab07.effects;

public interface IManageEffectsRepresentation {
    // Solicita a representção de um efeito sobre um Square
    public void createRepresentation(Effect e, int iSquarePos, int jSquarePos);

    // Solicita a remoção da representação de um efeito sobre um Square
    public void removeRepresentation(int iSquarePos, int jSquarePos);
}

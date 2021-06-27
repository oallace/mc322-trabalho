# Projeto *War of Nations*

Trabalho Final da disciplina de MC322 - Programação Orientada a Objetos na UNICAMP, SP.

## Descrição

Uma partida de xadrez não é mais apenas uma batalha entre peças brancas e peças pretas, agora é uma batalha entre nações.  

Cada nação tem habilidades especiais que mudam drasticamente o fluxo da partida: você não precisa se preocupar com o próximo movimento de uma peça se ela estiver congelada!

### Fluxo do Jogo

O jogo tem como base uma partida de xadrez entre duas nações, entretanto cada nação possui habilidades especiais que alteram um pouco a lógica do jogo original. Ganha o jogo quem der Xeque-Mate no oponente. 

#### Pontuação

Cada peça comida por um jogador possui um valor que será somado à sua pontuação, sendo:

* Peão: 1 ponto
* Cavalo: 3 pontos
* Bispo: 3 pontos
* Torre: 5 pontos
* Rainha: 9 pontos

#### Habilidades

Cada nação terá uma *Habilidade Básica* e uma *Habilidade Principal*, que podem ser usadas durante a partida. Cada habilidade possui um custo que deve ser descontado da pontuação do jogador para que seja utilizada. A *Habilidade Básica* custa 3 pontos e a *Habilidade Especial* custa 12 pontos.

### Nações

#### Nação de Gelo

* *Habilidade Básica:* Congela a casa selecionada. As peças sobre casas congeladas não podem se mover. Dura 1 turno.
* *Habilidade Especial:* Congela a casa selecionada e as suas casas imediatamente vizinhas.

#### Nação de Pedra

* *Habilidade Básica:* Levanta uma muralha de pedra na casa selecionada. Nenhuma peça, exceto pelo cavalo, pode realizar um movimento que passe por uma casa que possua uma muralha de pedra. Peças que estão em casas que possuem uma muralha podem sair. O efeito dura 1 turno.
* *Habilidade Especial:* Selecionando uma Casa, levantam-se muralhas de pedra nas suas casas vizinhas.

## Equipe

* Igor Henrique Buranello dos Santos - RA 171953
* Wallace Gustavo Santos Lima - RA 195512

## Vídeos do Projeto
### ![Vídeo da prévia](./assets/about/preview_video.mkv)

## Slides do Projeto
### ![Slides da prévia](./assets/about/preview_slides.pdf)

## Documentação dos Componentes

## Diagramas

### Diagrama Geral do Projeto
![Diagrama Geral do Projeto](./assets/about/general_diagram.png)  


* **View** deve fazer a ponte entre os usuários e o jogo em si. Ela recebe comandos (por meio de cliques com o *mouse*) e os comunica para a **Máquina de Estados**.    
* A **Máquina de Estados** é a responsável pela orquestração máxima do jogo: ela recebe da **Interface Gráfica** os comandos do usuário, administra os estados de jogo e informa as configurações dos demais componentes durante o jogo.    
* A **Máquina de Efeitos** recebe informações de uso de habilidades pelos jogadores, as configura e retorna informações sobre os efeitos que estão ativadas no momento. 

### Componente Effects
> Componente que gere os efeitos do jogo: congelamento e muralha de pedras. Com ele podemos atribuir efeitos e fazer consultas sobre efeitos ativos.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `effect.EffectMachineControl`
Autores | `Wallace Gustavo Santos Lima`
Interfaces | `IManageEffects` <br> `IAskEffects`

### Componente Effects
> Componente que gere os efeitos do jogo: congelamento e muralha de pedras. Com ele podemos atribuir efeitos e fazer consultas sobre efeitos ativos.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `effect.EffectMachineControl`
Autores | `Wallace Gustavo Santos Lima`
Interfaces | `IManageEffects` <br> `IAskEffects`


### Componente EffectsManagement
> Componente que gere os efeitos do jogo: congelamento e muralha de pedras. Com ele podemos atribuir efeitos e fazer consultas sobre efeitos ativos.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `effect.EffectMachineControl`
Autores | `Wallace Gustavo Santos Lima`
Interfaces | `IManageEffects` <br> `IAskEffects`


### Componente View
> Componente responsável pela apresentação gráfica do jogo. Recebe informações do usuário e se as comunica para a máquina de efeitos.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `view.Window`
Autores | `Wallace Gustavo Santos Lima` <br> `Igor Henrique Buranello dos Santos`
Interfaces | `IManageRepresentation`

### Componente Chess
> Componente principal do jogo. A partir dele podemos fazer perguntas sobre o estado do jogo e pedir movimentações.

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `chess.StateMachineControl`
Autores | `Wallace Gustavo Santos Lima` <br> `Igor Henrique Buranello dos Santos`
Interfaces | `IAskChess` <br> `IOperateChess` <br> `IChess`

**Detalhamento**

![Estrutura Interna Chess](./assets/about/chess_diagram.png)
O componente Chess é o mais complexo dos componentes do projeto, ele funciona com um sistema interno bastante complexo.

Ele funciona com base nos estados de jogo, classes **State** (*Carregamento, Início de Jogo, Início de turno, Seleção de Peça, Seleção de Habilidade, Seleção de Destino, Execução de Movimento, Fim de Turno e Fim de jogo*). Cada estado corresponde a uma etapa do jogo de xadrez que deve agir de uma maneira diferente.

Classes do Componente:

* O **Board** modela a plataforma de Xadrez e faz a comunicação com as **Squares**.
* Cada **Square** modela uma casa da plataforma de xadrez e faz a ligação com a **Piece** que está sobre ela.  
* **Piece** modela as peças do jogo de xadrez e possui instâncias de **Movement**, os quais fornecem informações sobre a movimentação das peças.
* **Player** representa um dos usuários do Jogo e contém informações relevantes como a Pontuação, a Nação ao qual ele pertence e e acesso as Habilidades que podem ser usadas.
* **Nation** representa as nações do jogo. Cada nação possui uma habilidade básica e uma habilidade principal que podem ser executadas.


### Interfaces

![Diagrama Interfaces](diagrama-interfaces.png)

Interface agregadora do componente em Java:

~~~java
public interface IDataSet extends ITableProducer, IDataSetProperties {
}
~~~

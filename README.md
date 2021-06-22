# Projeto <Título>
Versão em construção do trabalho final de MC322, na UNICAMP.  

**Até o momento** a Interface Gráfica foi implementada junto com o conjunto básico de peças e seus movimentos. Além disso, outros movimentos especiais do xadrez como o Roque, a Promoção de Peão e a captura *en passant*. A Máquina de Estados também já foi desenvolvida. Falta a contrução da Máquina de Efeitos e as análises de Xeque e Xeque-Mate. O jogo até então pode ser testado executando-se a classe *mc322.lab07.AppChess* em [out/production/lab07](./out/production/lab07).  

O código do projeto pode ser encontrado em [src/mc322/lab07](./src/mc322/lab07).



## Descrição
Uma partida de xadrez não é mais apenas uma batalha entre peças brancas e peças pretas, agora é uma batalha entre nações.  

Cada nação tem habilidades especiais que mudam drasticamente o fluxo da partida. Você não precisa se preocupar com o próximo movimento de uma peça se ela estiver congelada!  


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
Cada nação terá uma *Habilidade Básica* e uma *Habilidade Especial* que podem ser usadas durante a partida. Cada habilidade possui um custo que deve ser descontado da pontuação do jogador para que seja utilizada. A *Habilidade Básica* custa 5 pontos e a *Habilidade Especial* custa 12 pontos.

### Nações
#### Nação de Gelo
* *Habilidade Básica:* Congela a casa selecionada. As peças sobre casas congeladas não podem se mover. Dura 1 turno.
* *Habilidade Especial:* Congela a casa selecionada e as suas casas imediatamente vizinhas. A casa selecionada fica congelada por 3 turnos e as suas vizinhas por 1 turno.
#### Nação de Pedra
* *Habilidade Básica:* Levanta uma muralha de pedra na casa selecionada. A casa selecionada não pode possuir nenhuma peça sobre ela no momento de uso da habilidade. Nenhuma peça, exceto pelo cavalo, pode realizar um movimento que passe por uma casa que possua uma muralha de pedra. O efeito dura 1 turno.
* *Habilidade Especial:* Levanta uma muralha de pedra de 5 casas de comprimento. As peças dentro da muralha não podem se mover e nenhuma outra peça pode saltar a muralha. Dura 2 turnos.



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
* A **Interface Gráfica** deve fazer a ponte entre os usuários e o jogo em si. Ela recebe comandos (por meio de cliques com o *mouse*) e os comunica para a **Máquina de Estados**.    
* A **Máquina de Estados** é a responsável pela orquestração máxima do jogo: ela recebe da **Interface Gráfica** os comandos do usuário, administra os estados de jogo (*Carregamento, Início de Jogo, Início de turno, Seleção de Peça, Seleção de Habilidade, Seleção de Destino, Execução de Movimento, Fim de Turno e Fim de jogo*) e informa as configurações dos demais componentes durante o jogo.    
* A **Máquina de Efeitos** recebe informações de uso de habilidades pelos jogadores e as configura no **Tabuleiro** e retorna informações sobre as habilidades que estão ativadas no momento.      
* O **Jogador** representa um dos usuários do Jogo e contém informações relevantes como a Pontuação, a Nação ao qual ele pertence e as Habilidades que podem ser usadas.    
* O **Tabuleiro** modela a plataforma de Xadrez e faz a comunicação com as **Casas**.    
* Cada **Casa** modela uma casa da plataforma de xadrez e possui informações como a **Peça** que está sobre ela e o **Efeito** ao qual ela está submetida.  
* A **Peça** modela as peças do jogo de xadrez e possui instâncias de **Movimento**, o qual controla os movimentos das peças.    
* Por fim, **Efeito** é um modelo dos efeitos de habilidades do jogo.

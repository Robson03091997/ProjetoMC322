# ProjetoMC322README.txt
==========

Projeto: Simulador de Robôs em Ambiente Virtual

Descrição:
-----------
Este projeto simula diferentes tipos de robôs (terrestres e aéreos) operando em ambientes tridimensionais. Os robôs podem se mover, detectar obstáculos e utilizar sensores para monitorar o ambiente. O sistema oferece um menu interativo para gerenciamento de ambientes, robôs e sensores.

Estrutura dos Arquivos:
------------------------
- Ambiente.java
  - Define o ambiente virtual (largura, comprimento, altura).
  - Gerencia a frota de robôs e a lista de obstáculos.
  - Detecta colisões e controla limites de movimentação.

- Obstaculo.java
  - Representa obstáculos tridimensionais.
  - Verifica se uma posição está bloqueada.

- Robo.java
  - Classe base para todos os tipos de robôs.
  - Gerencia posição, direção, sensores e o ambiente.

- Sensor.java
  - Classe abstrata para todos os sensores dos robôs.
  - Define métodos comuns como `monitorar()`, `getAlcance()`, etc.

- SensorProximidade.java
  - Sensor que calcula distância entre robôs e obstáculos.
  - Especialização de `Sensor`.

- SensorTemperatura.java
  - Sensor que monitora temperatura (simulação).
  - Especialização de `Sensor`.

- RoboTerrestre.java
  - Robô terrestre básico.

- RoboPreparaSolo.java
  - Robô especializado em preparar o solo.

- RoboColheitadera.java
  - Robô especializado em colher recursos.

- RoboAereo.java
  - Robô aéreo básico.

- RoboDrone.java
  - Versão de robô aéreo, tipo drone.

- RoboJato.java
  - Robô aéreo de alta velocidade, tipo jato.

- Main.java
  - Menu interativo em console.
  - Permite selecionar ambientes, listar robôs e obstáculos, mover robôs, ativar/desativar sensores.
  - Exemplo de instanciamento de ambientes, robôs e sensores.

Requisitos:
-----------
- Java 8 ou superior.
- Terminal/Shell ou IDE como Eclipse, IntelliJ ou VSCode para compilar e rodar.

Compilação:
-----------
1. Compile todos os arquivos `.java`:

   ```bash
   javac Main.java

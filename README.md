# Thread-SO

Se trata de um programa que usará a programação MultiThreading para dividir uma matriz de números inteiros aleatórios e contar quantos números primos existem nela

# Linux:
.
.
.


# Windows:

### 1. Verificando a Instalação do Java
Para verificar se o Java está instalado usando seguinte comando no CMD:

```java -version```

caso a saída seja parecida com a imagem abaixo o Java está instalado e podemos avançar para o **Passo 2**:

![java -version](https://user-images.githubusercontent.com/71159051/180118070-27e424cf-292b-4d00-a373-2ec1a8e271a6.png)

***caso contrário faça a instalação do Java no diretório padrão sugerido na instalação.***


### 2. Verificando variável de ambiente Java:
Para verificar se a variável de ambiente Java está configurada corretamente, execute o seguinte comando no CMD:

```echo %JAVA_HOME%```

caso a saída seja parecida com a imagem abaixo a variável está configurada corretamente e podemos passar para o **Passo 3**:

![2](https://user-images.githubusercontent.com/71159051/180118410-0f66654c-ea4f-4617-ae39-52575d62bd03.png)

caso a saída for parecida com:

![22](https://user-images.githubusercontent.com/71159051/180118476-f857d046-a7af-431a-977c-9627a7d86390.png)

siga para o **Passo 2.1** para configurar a variável de ambiente.


### &emsp; &emsp; 2.1 Configurando a variável de Ambiente
> :warning: ***OBS: Para configurar a variável de ambiente é preciso abrir o CMD como ADMINISTRADOR.***

&emsp; &emsp; Após executar o CMD como Administrador execute o seguinte comando, para mostrar ao prompt de comando o caminho padrão onde o 
&emsp; &emsp; Java foi instalado:

&emsp; &emsp; ```setx -m JAVA_HOME “C:\Program Files\Java\jdk1.8.0_321”```

&emsp; &emsp; caso a saída seja parecida com a imagem abaixo, a variável foi configurada corretamente e podemos avançar para o **Passo 3**: 

&emsp; &emsp;
![3](https://user-images.githubusercontent.com/71159051/180118770-35762cde-c146-4d20-974f-f7190e93d6b8.png)


### 3. Navegue Até a Pasta do Projeto:
Para executar o projeto Thread-SO é preciso estar na pasta dele, portanto abra o CMD e navegue até a pasta onde está o projeto usando o comando:

```cd diretorio_onde_esta_a_pasta_do_Projeto```

em seguida navegue até a pasta dist/ onde está o arquivo .jar do projeto, usando:

```cd dist```

como na imagem abaixo:

![3](https://user-images.githubusercontent.com/71159051/180119781-30197cb1-1baa-4577-8e3b-ef30fd172ae0.png)




### 4. Executando o Projeto por Linha de Comando:
Para rodar o projeto execute o seguinte comando no CMD após ter acessado o diretório dist\ como no passo anterior:

```java -jar -Xms0m -Xmx4G Thread-SO.jar```

será aberto o seguinte menu:

![5](https://user-images.githubusercontent.com/71159051/180119817-7133ef1f-ed49-4132-9395-84ddfa7384c7.png)

para executar para casos onde a matriz ocupe mais de 4G de memória altere 4G para a quantidade máxima de memória RAM disponível no sistema.














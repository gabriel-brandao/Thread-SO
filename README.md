# Thread-SO

Se trata de um programa que usará a programação MultiThreading para dividir uma matriz de números inteiros aleatórios e contar quantos números primos existem nela

# Linux:

### 1. Instalação do Java

#### &emsp; &emsp; 1.1 Via .tar.gz
&emsp; &emsp; Faça o Download do Java JDK compactado em .tar.gz, em seguida crie o diretório jvm em ```/usr/lib/```:

&emsp; &emsp; ```sudo mkdir /usr/lib/jvm```

&emsp; &emsp; Em seguida descompacte o JDK no diretório criado acima:

&emsp; &emsp; ```sudo tar zxvf jdk-18_linux-x64_bin.tar.gz -C /usr/lib/jvm```

&emsp; &emsp; Renomeie a pasta movida para dentro do diretório ```/usr/lib/jvm``` como "jdk". Se ao executar o comando abaixo ocorrer um erro com a
&emsp; &emsp; mensagem iniciando com _“mv: é impossível sobrescrever o não-diretório”_, pule este passo:

&emsp; &emsp; ```sudo mv /usr/lib/jvm/jdk* /usr/lib/jvm/jdk```

&emsp; &emsp; Crie um link simbólico para a pasta criada:

&emsp; &emsp; ```sudo ln -s /usr/lib/jvm/jdk /usr/lib/jvm/java-oracle```

&emsp; &emsp; Concluido os passos acima, vamos configurar a variável de ambiente no **Passo 2.**

### 2. Verificando variável de ambiente Java:
Para verificar se a variável de ambiente Java está configurada corretamente, execute o seguinte comando no TERMINAL:

```echo $JAVA_HOME```

caso a saída seja parecida com a imagem abaixo a variável está configurada corretamente e podemos passar para o **Passo 3**:

![image](https://user-images.githubusercontent.com/71159051/180905653-969c5ed9-8681-458e-8e8c-a5410eb180f1.png)

caso a saída for parecida com:

![image](https://user-images.githubusercontent.com/71159051/180905753-f174b47c-ef86-42ac-b851-48dfbe030dc2.png)

siga para o **Passo 2.1** para configurar a variável de ambiente.


### &emsp;&emsp;2.1 Configurando a variável de Ambiente

&emsp;&emsp; Crie uma cópia do arquivo ```/etc/profile```:

&emsp;&emsp; ```sudo cp -a /etc/profile /etc/profile.original```

&emsp;&emsp; Abra o arquivo com seu editor de texto:

&emsp;&emsp; ```sudo gedit /etc/profile```

&emsp;&emsp; Cole o texto abaixo dentro do arquivo logo após os comentários(#):

```
JAVA_HOME=/usr/lib/jvm/java-oracle/
PATH=$JAVA_HOME/bin:$PATH export PATH JAVA_HOME
CLASSPATH=$JAVA_HOME/lib/tools.jar
CLASSPATH=.:$CLASSPATH
export  JAVA_HOME  PATH  CLASSPATH
```

&emsp;&emsp; Edite o arquivo de variavel de ambiente com o seguinte comando:

&emsp;&emsp; ```sudo gedit /etc/environment```

&emsp;&emsp; Adicione o caminho onde o java está instalado

&emsp;&emsp; ```JAVA_HOME="/usr/lib/jvm/java-oracle/bin/java"```

&emsp;&emsp; Aplique as mudanças executando:

&emsp;&emsp; ```source /etc/environment```

&emsp;&emsp; Reinicie o computador para aplicar as alterações e volte ao **passo 2** para verificar se a instalação foi feita corretamente.



### 3. Navegue Até a Pasta do Projeto:
Para executar o projeto Thread-SO é preciso estar na pasta dele, portanto abra o TERMINAL e navegue até a pasta onde está o projeto usando o comando:

```cd diretorio_onde_esta_a_pasta_do_Projeto```

em seguida navegue até a pasta ```dist/``` onde está o arquivo .jar do projeto, usando:

```cd dist```

como na imagem abaixo:

![3](https://user-images.githubusercontent.com/71159051/180119781-30197cb1-1baa-4577-8e3b-ef30fd172ae0.png)


### 4. Executando o Projeto por Linha de Comando:
Para rodar o projeto execute o seguinte comando no CMD após ter acessado o diretório ```dist\``` como no passo anterior:

```java -jar -Xms0m -Xmx4G Thread-SO.jar```

será aberto o seguinte menu:

![5](https://user-images.githubusercontent.com/71159051/180119817-7133ef1f-ed49-4132-9395-84ddfa7384c7.png)

para executar para casos onde a matriz ocupe mais de 4G de memória altere 4G para a quantidade máxima de memória RAM disponível no sistema.


# Windows:

### 1. Verificando a Instalação do Java
Para verificar se o Java está instalado usando seguinte comando no CMD:

```java -version```

caso a saída seja parecida com a imagem abaixo o Java está instalado e podemos avançar para o **Passo 2**:

![java -version](https://user-images.githubusercontent.com/71159051/180118070-27e424cf-292b-4d00-a373-2ec1a8e271a6.png)

> :floppy_disk: **caso contrário faça a instalação do Java no diretório padrão sugerido na instalação, e execute o ***Passo 1*** novamente !**


### 2. Verificando variável de ambiente Java:
Para verificar se a variável de ambiente Java está configurada corretamente, execute o seguinte comando no CMD:

```echo %JAVA_HOME%```

caso a saída seja parecida com a imagem abaixo a variável está configurada corretamente e podemos passar para o **Passo 3**:

![2](https://user-images.githubusercontent.com/71159051/180118410-0f66654c-ea4f-4617-ae39-52575d62bd03.png)

caso a saída for parecida com:

![22](https://user-images.githubusercontent.com/71159051/180118476-f857d046-a7af-431a-977c-9627a7d86390.png)

siga para o **Passo 2.1** para configurar a variável de ambiente.


### &emsp; &emsp; 2.1 Configurando a variável de Ambiente
>&emsp; &emsp;:warning: ***OBS: Para configurar a variável de ambiente é preciso abrir o CMD como ADMINISTRADOR !!!***

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
Para rodar o projeto execute o seguinte comando no CMD após ter acessado o diretório ```dist\``` como no passo anterior:

```java -jar -Xms0m -Xmx4G Thread-SO.jar```

será aberto o seguinte menu:

![5](https://user-images.githubusercontent.com/71159051/180119817-7133ef1f-ed49-4132-9395-84ddfa7384c7.png)

para executar para casos onde a matriz ocupe mais de 4G de memória altere 4G para a quantidade máxima de memória RAM disponível no sistema.














# Gerador de Código C

Este projeto implementa um gerador de código em C para a **Linguagem Algorítmica**, uma linguagem educacional desenvolvida pelo professor Jander (DC/UFSCar). Ele analisa arquivos de código fonte escritos nessa linguagem e aponta erros léxicos, sintáticos ou semânticos e para casos onde não há erro, a saída contém o código gerado em C.

Este trabalho é a **parte 5 (T5)** da disciplina de Compiladores e utiliza **ANTLR 4** para geração de analisadores e **Maven** como sistema de build.

O guia a seguir apresenta os requisitos e as etapas para compilar e executar o projeto corretamente.

<br>

## 📋 Requisitos
Antes de mais nada, certifique-se de ter os seguintes programas instalados:

![Java](https://img.shields.io/badge/java-22+-orange)
![Maven](https://img.shields.io/badge/maven-3.6+-blue)
![Git](https://img.shields.io/badge/Git-installed-orange?logo=git)

- Java JDK (versão 22 ou superior)
- Apache Maven (versão 3.6 ou superior)
- Git (para clonar o repositório)

<br>

## 💻 IDE
O projeto foi desenvolvido utilizando o **NetBeans 25**, que oferece integração nativa com Maven e suporte a ANTLR.

Para facilitar seu uso, é recomendado que utilize o mesmo ambiente, pois o tutorial a seguir utiliza atalhos disponíveis pelo próprio NetBeans.

<br>

## 📔 Guia de Execução

**1. Clonar o repositório**

Aqui você pode clonar onde preferir, vamos utilizar o NetBeans para acessar a estrutura do projeto.
```
git clone https://github.com/enzomurayama/compiladores.git
```
<br>

**2. Abrir o projeto e baixar suas dependências**

Para facilitar o trabalho, abra o projeto com o NetBeans:

- Abra o NetBeans
- Vá em File > Open Project...
- Selecione a pasta clonada do repositório com o projeto (compiladores/T5/AnalisadorSemantico) e clique em Open Project.
- O NetBeans detectará automaticamente o projeto Maven e configurará as dependências.

<br>

Às vezes, o NetBeans não baixa automaticamente as dependências declaradas no pom.xml. Caso isso ocorra:

- Dentro do NetBeans, clique com o botão direito na pasta *Dependencies*
- Selecione a opção Download Declared Dependencies

<br>

Alternativa via terminal
```
mvn dependency:resolve
```

<br>

**3. Compilação**

Para compilar, podemos utilizar a própria ferramenta do NetBeans. Dentro da IDE, acesse a opção **Run > Build Project** no menu do topo.

<br>

**4. Execução**

A execução utiliza um arquivo de entrada, simulando um programa na Linguagem Algorítmica (desenvolvida pelo prof. Jander, no âmbito do DC/UFSCar), e produz um arquivo de saída, com os erros identificados neste programa ou, em caso de sucesso, o código gerado em C.

Então, em linha de comando:

```
java -jar [caminho-para-programa.jar] [caminho-para-arquivo-entrada.txt] [caminho-para-arquivo-saída.txt]
```
<br>

> O *programa.jar*, após compilado, está localizado em AnalisadorSemantico/target/AnalisadorSemantico-1.0-SNAPSHOT-jar-with-dependencies.jar

<br>

Exemplo de comando para a execução do programa:
```
c:\java -jar c:\compilador\AnalisadorSemantico-1.0-SNAPSHOT-jar-with-dependencies.jar c:\casos-de-teste\arquivo1.txt c:\temp\saida.txt
```

<br>

Além disso, este repositório contém dois arquivos de exemplo, um de entrada (*ArquivoExemploLA.txt*) e o seu resultado como saída (*ArquivoSaidaLA.txt*). É possível testar utilizando estes mesmos arquivos.

<br>

## 🛠 Casos de Teste
Para este trabalho, o professor disponibilizou alguns casos de teste que podem ser encontrados [aqui](https://drive.google.com/file/d/1SwQg-O3dG_N5okejIwTe1ujFUjffw6R_/view).

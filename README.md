# MP3 Servlet
O MP3 Servlet é um simples servlet feito para modificar facilmente tags ID3 de uma música no formato .mp3

Esta é uma aplicação feita para o projeto final de Computação II em 2021.2 na UFRJ para o professor Jose Augusto Sapienza Ramos. O objetivo era criar um Servlet totalmente funcional em Java usando o GlassFish para editar metadados de um arquivo de áudio MPEG3.

  <details><summary>English</summary>
  <p>

  >MP3 Servlet is a simple servlet made to modify an ID3 tag of a given MP3 file. 

  >This was an application developed for the final project in Object-Oriented Programming in Java (Computação II), in 2022 at Federal University of Rio de Janeiro. The objective was to create a working java servlet tool to edit metadata of an MPEG3 audio file using GlassFish.

  </details> 

## Funcionamento

- Ao fazer deploy do .war no GlassFish 5 usando Java 1.8 (Temurin-1.8), o caminho para a página inicial é em ***/TrabalhoFinal-1.0-SNAPSHOT/api/file***

- Na página inicial, será requisitado o MP3 a ser lido ou modificado[^1]:

![Captura de Tela 2022-03-09 às 22 29 46](https://user-images.githubusercontent.com/69607669/157572508-55058b0e-312b-4e3f-8d7b-56643cb0e80d.png)

- Logo após, será mostrado os metadados do arquivo enviado e será disponível para alteração:

![Captura de Tela 2022-03-09 às 22 32 01](https://user-images.githubusercontent.com/69607669/157572817-e9ec86d4-e045-4aaf-8d9a-4347ea8f09f1.png)

- Após clicar em salvar, poderá ser feito o download do arquivo editado de acordo com o que foi colocado na página anterior.
    > Atenção: A capa de álbum preexistente na música será mantida após a alteração dos metadados

![Captura de Tela 2022-03-09 às 22 32 19](https://user-images.githubusercontent.com/69607669/157573052-4adbed2c-eb01-451a-bcb2-80b5b3692a2b.png)

## Agradecimentos
 - Ao nosso professor Jose Augusto Sapienza Ramos
 - [mp3agic](https://github.com/mpatric/mp3agic)

## Autores
 - Manoel Marcelo da Silva [@manoelmms](https://github.com/manoelmms)
 - Lucas de Lyra Monteiro [@LucasdeLyra](https://github.com/LucasdeLyra)

[^1]:Na pasta ***src/test/resources/*** estão disponíveis exemplos de arquivos .mp3 funcionais e incompartíveis para o teste do programa (arquivos fornecidos por mp3agic)


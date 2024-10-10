# :currency_exchange: Challenge Conversor de Moedas ONE ![MIT License](https://img.shields.io/badge/License-MIT-green.svg)
Challenge Conversor de Moedas implementado com a [API ExchangeRate](https://www.exchangerate-api.com).<br>
Proposto pela Oracle Next Education, na formação de Back-end Java, em parceria com a Alura.
<br>
img alt="Badge da Conclusão do Challenge" src="Badge-Conversor.png" align="center">
<br>
## :wrench: Sobre o projeto
- Uma aplicação back-end simples e robusta para conversão de moedas.
- A aplicação consome uma API que retorna taxas de câmbio confiáveis e em tempo real.
- Suporta a conversão entre 9 moedas diferentes (pré-definidas, mas o programa é escalável para mais moedas).
> Você pode checar as moedas suportadas no Enum "Currencies" implementado na classe "FactoryRecordCurrency" que se encontra no pacote "model".
<br>
## :money_with_wings: Como funciona?
- A aplicação utiliza o endpoint de pares de códigos bases da API.
- Após o usuário selecionar ambas as moedas, dois códigos são inseridos no GET da API e assim é realizado o request.
- O usuário deve também inserir o valor que deseja converter.
- O endpoint retorna a taxa de câmbio em double, e assim a conversão é realizada, multiplicando o valor inserido pelo usuário pela taxa de câmbio retornada.
- O usuário recebe o valor retornado, e pode realizar uma conversão novamente, ou sair da aplicação.
- Caso o usuário saia da aplicação, é gerado um log com o histórico de conversões que realizou naquele momento. O número máximo de conversões realizadas pelo usuário armazenadas é de 5 (cinco).
> O log é armazenado na pasta resources, dentro da pasta gerada de acordo com o respectivo dia em que a aplicaçao foi utilizada.
<br>
## :computer: Tecnologias e ferramentas utilizadas
- Java (JDK 17)
- IntelliJ e Git
- [ExchangeRate-API](https://www.exchangerate-api.com)

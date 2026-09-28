# IMC Check

Aplicativo Android para calcular o Índice de Massa Corporal (IMC) de forma rápida e simples. Basta informar altura e peso para ver o resultado e a classificação, com uma cor que indica a faixa em que você se encontra.

## Funcionalidades

- Cálculo do IMC a partir da altura (cm) e do peso (kg)
- Classificação segundo a tabela da OMS
- Cor do resultado de acordo com a faixa de IMC
- Aceita vírgula ou ponto como separador decimal
- Validação de dados inválidos, com mensagem de erro
- Botão para limpar os campos
- Tela com rolagem, adequada a telas pequenas e teclado aberto

## Como o IMC é calculado

```
IMC = peso (kg) / altura (m)²
```

O resultado é arredondado para uma casa decimal, e a classificação usa esse mesmo valor arredondado, para que o número exibido e a faixa sempre correspondam.

## Classificação

| IMC             | Classificação      |
|-----------------|--------------------|
| Abaixo de 18,5  | Abaixo do peso     |
| 18,5 a 24,9     | Peso normal        |
| 25,0 a 29,9     | Sobrepeso          |
| 30,0 a 34,9     | Obesidade grau I   |
| 35,0 a 39,9     | Obesidade grau II  |
| 40,0 ou mais    | Obesidade grau III |

## Tecnologias

- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material 3](https://m3.material.io/)

## Estrutura do projeto

```
app/src/main/java/com/example/appimc/
├── MainActivity.kt      # Tela principal (Compose) e interface
└── ImcCalculadora.kt    # Modelo ImcResultado e função calcularImc()
```

## Como executar

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Abra o projeto no **Android Studio** (versão recente).
3. Aguarde a sincronização do Gradle.
4. Selecione um emulador ou dispositivo físico.
5. Clique em **Run** (▶).

### Requisitos

- Android Studio Ladybug ou mais recente
- JDK 17
- Dispositivo ou emulador com Android 7.0 (API 24) ou superior

## Aviso

O IMC é apenas um indicador de referência e não substitui a avaliação de um profissional de saúde. Ele não considera fatores como massa muscular, idade, sexo e distribuição de gordura corporal.

## Licença

Defina aqui a licença do projeto (por exemplo, MIT).

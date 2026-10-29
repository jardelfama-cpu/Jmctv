# Jmctv

Aplicativo Android TV para listar canais e reproduzir streams em um player de vídeo.

## Como abrir no Android Studio

1. Instale o Android Studio e o SDK do Android.
2. Abra a pasta do projeto em um novo projeto Android Studio.
3. Aguarde o Gradle sincronizar.
4. Conecte um emulador Android TV ou aparelho físico.
5. Clique em Run > Run 'app'.

## Onde trocar os canais

Edite o arquivo:

`app/src/main/java/com/jmctv/app/ChannelRepository.kt`

Troque os URLs de exemplo pelos links reais dos canais que você quer reproduzir.

## Estrutura principal

- `MainActivity.kt` - tela com a lista de canais
- `PlayerActivity.kt` - player de vídeo com ExoPlayer
- `ChannelRepository.kt` - lista de streams e nomes dos canais

## Observações

Este projeto usa a biblioteca Leanback para Android TV e o ExoPlayer para reprodução. Os links atuais são demonstrativos e devem ser substituídos por streams reais do seu provedor.

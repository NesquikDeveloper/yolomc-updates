# Anti Cristo (AteuMC)
Essa é a source principal da Yolo, estruturada no Gradle para a versão 1.8.x.

O projeto foi pensado para rodar uma rede completa, desde a base de dados com Redis até os minigames prontos: Hunger Games, BedWars, Duels, PvP e Lobby. Ou seja, praticamente a rede inteira já vem no pacote, porque aparentemente criar algo do zero é trabalho demais pra Desenvolvedor Full Stack. Melhor pegar source dos outros, trocar o package, dar uma maquiada no Cursor e vender como se fosse projeto próprio. Mas é claro, não é indireta para ninguém... 400 repositórios, é você?

---

## O que tem em cada lixo?
* **`core`**  
  O cabeção. Guarda tudo que não depende diretamente do Bukkit ou Bungee: sistema de contas, cargos/tags, clãs, party, punições (ban/mute), conexões com MySQL/Redis e a API da Mojang.
* **`core-bukkit`**  
  A base que vai dentro de todos os servidores Spigot/Bukkit. Cuida dos menus de inventário, scoreboard sem piscar o cu, pacotes (NMS).
* **`core-proxy`**  
  O plugin do BungeeCord. Gerencia o direcionamento dos jogadores entre os servidores, staffchat global, sincronização de grupos/partys e alertas de punição em tempo real.
* **`auth`**  
  Sistema de autenticação para login e registro, suporte a contas originais e piratas, proteção contra bots e sistema de captcha (menu desgraçado por value).
* **`lobby`**  
  Lobbys dos games (Duels, Bedwars etc..). Inclui os select de hubs (slimeworldmanager ajuda nisso pkrl), cosméticos, músicas em noteblock (Por evento: Halloween, Natal, etc..) e NPCs (Loja, Game).
* **`hungergames`**  
  Essa porra de HG. Sistema completo de kits com umas habilidades do redbull que te dar asas, spawn automático de Feast, Mini-Feast, Coliseu, evento de batalha final e sistema de espectadores (suporte somente com bo3).
* **`bedwars`**  
  Engine de BedWars com suporte aos modos Solo, Duplas, Trio e Quarteto. *Não fiz esse Duels 1v1/2v2/3v3/4v4*, a correção do bedwars foi feita em outra source mais coloco nessa sim
* **`pvp`**  
  Esse é pica! Arena com doublekit (Não coloco singlekit coisa de baitola), FPS, Lava Challenge e MLG.
* **`duels`**  
  Sistema de 1v1/2v2. Modos Gladiator, Soup, UHC, Sumo e NoDebuff.
* **`limbo`**  
  Servidor leve para segurar jogadores AFK.
* **`terrain-generator`**  
  Essa bosta estou olhando maneiras de deixar ele coisado sem pesar no carregamento do servidor.
* **`build-world`**  
  Mundo utilitário focado na equipe de builders criarem, testarem e salvarem as construções dos mapas.

---

## O que é Redis? (Se fuder né caralho?)
1. **MySQL**: Guarda a mãe do Putt0 e, status, rank dos jogadores, clãs, punições e tudo aquilo que você não quer perder porque resolveu reiniciar o servidor.
2. **Redis**: Faz a mágica do tempo real. Quando um servidor abre uma sala, fecha uma vaga ou um jogador leva ban/mute, o Redis avisa todos os outros servidores e o Bungee na hora, através do Pub/Sub. Porque aparentemente esperar o banco de dados responder toda vez seria uma ótima ideia.
3. **Assíncrono**: Quase todas as operações pesadas banco de dados, skins, webhooks do Discord etc. rodam fora da thread principal do servidor, para evitar travamentos no tick do server. Afinal, ninguém quer que o servidor congele por 3 segundos só porque algum Zé Pelintra resolveu abrir o menu de cosméticos.

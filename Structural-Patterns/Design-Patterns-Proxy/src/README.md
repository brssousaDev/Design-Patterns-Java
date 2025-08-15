# Projeto Exemplo – Padrão Proxy (Simples e Spring Cache)

Este projeto demonstra, de forma **bem simples**, dois usos do padrão de projeto **Proxy** em Java:

1. **Proxy de Controle de Acesso**
2. **Proxy de Cache com Spring**

---
# Projeto Exemplo – Padrão Proxy (Simples)

Este projeto demonstra, de forma **bem simples**, o uso do padrão de projeto **Proxy** em Java.

O objetivo é criar um **intermediário** (`GeraRelatorioProxy`) que controla o acesso a um recurso real (`GeraRelatorioReal`), liberando ou negando a execução com base no usuário.

---

## Estrutura

- **ProxyInterface** → Interface com o método `readReport()`.
- **GeraRelatorioReal** → Classe real que executa a leitura do relatório.
- **GeraRelatorioProxy** → Classe proxy que verifica se o usuário tem permissão.
- **Main** → Classe de teste, simulando um usuário autorizado e outro não autorizado.

---

## Funcionamento

1. O cliente cria uma instância de `GeraRelatorioProxy`, informando o nome do usuário.
2. O proxy verifica se o usuário é `"admin"`.
3. Se for autorizado:
   - Cria uma instância de `GeraRelatorioReal`.
   - Executa a leitura do relatório.
4. Se não for autorizado:
   - Mostra mensagem de acesso negado.

---

## Exemplo de Saída

**Usuário autorizado (`admin`):**
```
=== Teste com usuário autorizado ===
[Proxy] Criando instância do Proxy em: 20:17:31.079439907
[Proxy] Iniciando Leitura do relatório em: 20:17:31.086066530
Relatório lido com sucesso!
[Proxy] Finalizando Leitura do relatório em: 20:17:34.687376080
```

> ⏱ Diferença de ~3 segundos entre início e fim devido ao carregamento do objeto real.

**Usuário não autorizado (`user`):**
```
=== Teste com usuário não autorizado ===
[Proxy] Criando instância do Proxy em: 20:17:34.688128397
[Proxy] Iniciando Leitura do relatório em: 20:17:34.688310312
Acesso negado! Usuário não autorizado.
[Proxy] Finalizando Leitura do relatório em: 20:17:34.688530091
```

## Proxy com Spring Cache

No segundo exemplo, o padrão **Proxy** é aplicado de forma **transparente** pelo **Spring Cache**, evitando acessos repetidos e demorados a um recurso.

- A interface `BookRepository` define o método `findBookByIsbn`, anotado com `@Cacheable("books")`.
- A implementação `BookRepositoryImpl` simula um serviço lento usando `Thread.sleep`, representando uma operação custosa.
- O Spring cria **dinamicamente** um **proxy** do `BookRepository` que:
   1. Intercepta as chamadas ao método.
   2. Verifica se o resultado para aquele ISBN já está no cache.
   3. Se **não** estiver, executa o método real e armazena o retorno no cache.
   4. Se **já** estiver, retorna o valor diretamente do cache, sem executar o serviço lento.

No teste (`ProxySpringCacheApplication`):
- A **primeira** chamada para cada ISBN executa o método real (lento).
- As chamadas seguintes para o **mesmo** ISBN retornam imediatamente, demonstrando o ganho de desempenho.

**Saída esperada:**
- As primeiras buscas para cada ISBN mostram `"Simulating a slow service..."`.
- As buscas repetidas para o mesmo ISBN **não** mostram essa mensagem, evidenciando que o proxy do Spring Cache está retornando do cache.

### Exemplo de Saída (Spring Cache)

```
Fetching book with ISBN: 1
Simulating a slow service...

First call: Book{isbn='978-0134685991', title='Effective Java', author='Joshua Bloch'}

Fetching book with ISBN: 2
Simulating a slow service...

Second call: Book{isbn='978-0596009205', title='Head First Design Patterns', author='Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson'}
Third call: Book{isbn='978-0134685991', title='Effective Java', author='Joshua Bloch'}

Fetching book with ISBN: 3
Simulating a slow service...

Fourth call: Book{isbn='978-1617294945', title='Spring in Action', author='Craig Walls'}
Fifth call: Book{isbn='978-1617294945', title='Spring in Action', author='Craig Walls'}
Sixth call: Book{isbn='978-0596009205', title='Head First Design Patterns', author='Eric Freeman, Bert Bates, Kathy Sierra, Elisabeth Robson'}
```
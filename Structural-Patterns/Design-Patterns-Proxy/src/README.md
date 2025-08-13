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

```
**Usuário não autorizado (`user`):**
=== Teste com usuário não autorizado ===
[Proxy] Criando instância do Proxy em: 20:17:34.688128397
[Proxy] Iniciando Leitura do relatório em: 20:17:34.688310312
Acesso negado! Usuário não autorizado.
[Proxy] Finalizando Leitura do relatório em: 20:17:34.688530091
```
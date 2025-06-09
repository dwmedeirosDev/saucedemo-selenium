Feature: Consultar carrinho
  Como usuário logado no site SauceDemo,
  Quero escolher um produto e adicioná-lo ao carrinho de compras,
  Para verificar se o produto no carrinho confere com o que foi escolhido.

  Scenario Outline: Validar se o produto e preço no carrinho são iguais aos selecionados
    Given que acesso o site "https://www.saucedemo.com/"
    When insiro o usuário "<username>" e a senha "<password>"
    And clico no botão Login
    Then sou redirecionado para a página Inventory
    
    When valido o nome "<nomeProduto>" e o preço "<precoProduto>"
    And clico no produto
    Then sou redirecionado para a página Inventory Item

    When valido novamente o nome "<nomeProduto>" e o preço "<precoProduto>"
    And clico no botão Add to cart
    And clico no ícone do carrinho

    Then sou redirecionado para a página Cart
    And valido que o produto "<nomeProduto>" com o preço "<precoProduto>" está presente no carrinho

  Examples:
    | nomeProduto         | precoProduto | username      | password     |
    | Sauce Labs Backpack | $29.99       | standard_user | secret_sauce |

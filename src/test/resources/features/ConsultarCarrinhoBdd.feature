Feature: Consultar carrinho
  Como usuário logado no site SauceDemo,
  Quero escolher um produto e adicioná-lo ao carrinho de compras,
  Para verificar se o produto no carrinho confere com o que foi escolhido.

  Scenario Outline: Validar se o produto e preço no carrinho são iguais aos selecionados
    Given que acesso o site "https://www.saucedemo.com/"
    When insiro o usuário "<username>" e a senha "<password>"
    And clico no botão Login
    Then sou redirecionado para a página Inventory
    When valido o sku "<skuProduto>" e o nome "<nomeProduto>"
    And clico no produto com sku "<skuProduto>"
    Then sou redirecionado para a página Inventory Item
    When valido novamente o nome "<nomeProduto>" e o preço "<precoProduto>"
    And clico no botão Add to cart
    And clico no ícone do carrinho
    Then sou redirecionado para a página Cart
    And valido que o produto "<nomeProduto>" com sku "<skuProduto>" e o preço "<precoProduto>" estão presentes no carrinho de compra

    Examples:
      | skuProduto | nomeProduto             | precoProduto | username      | password     |
      |          1 | Sauce Labs Bolt T-Shirt | $15.99       | standard_user | secret_sauce |
      |          2 | Sauce Labs Onesie       | $7.99        | standard_user | secret_sauce |
      |          4 | Sauce Labs Backpack     | $29.99       | standard_user | secret_sauce |

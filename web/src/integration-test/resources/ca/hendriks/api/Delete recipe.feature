Feature: Delete recipe

  In order for recipes and user inventories to be accurate
  As an administrator
  I want to delete a recipe

  Scenario: The administrator deletes a recipe

    Given a recipe named "The Abbey Cocktail" with method "Shake well and strain into cocktail glass":
      | Quantity | Unit    | Ingredient        |
      | 1/2      | MEASURE | Dry Gin           |
      | 1/4      | MEASURE | Kina Lillet       |
      | 1/4      | MEASURE | Orange juice      |
      | 1        | DASH    | Angostura Bitters |

    When the administrator deletes the recipe named "The Abbey Cocktail"

    Then there will be 0 recipes available
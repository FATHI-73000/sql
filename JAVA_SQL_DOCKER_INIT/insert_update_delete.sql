-- ========================================================
-- 1️⃣ Créer une commande de 3 articles différents
-- ========================================================

INSERT INTO orders (customer_id, order_date)
VALUES (1, NOW());

SELECT LAST_INSERT_ID() AS new_order_id;

-- Supposons que new_order_id = 10
INSERT INTO order_product (order_id, product_id, quantity)
VALUES
(10, 1, 2),
(10, 2, 1),
(10, 3, 5);

-- ========================================================
-- 2️⃣ Ajouter un produit avec sa catégorie et sa quantité
-- ========================================================

-- Si la catégorie existe déjà
INSERT INTO products (name, category_id, price, stock)
VALUES ('Nouveau Produit', 1, 15.50, 20);

-- Si la catégorie n’existe pas
INSERT INTO categories (name) VALUES ('Nouvelle Catégorie');
SELECT LAST_INSERT_ID() AS new_category_id;

INSERT INTO products (name, category_id, price, stock)
VALUES ('Produit Catégorie Nouvelle', <new_category_id>, 25.00, 50);

-- ========================================================
-- 3️⃣ Ajouter 100 à la quantité en stock d’un produit
-- ========================================================

UPDATE products
SET stock = stock + 100
WHERE id = 2;

-- ========================================================
-- 4️⃣ Augmenter de 5% le prix des produits d’une catégorie donnée
-- ========================================================

UPDATE products
SET price = price * 1.05
WHERE category_id = 1;

-- ========================================================
-- 5️⃣ Supprimer un article
-- ========================================================

-- Supprimer d'abord de order_product si nécessaire
DELETE FROM order_product
WHERE product_id = 3;

-- Puis supprimer le produit
DELETE FROM products
WHERE id = 3;

-- ========================================================
-- 6️⃣ Supprimer les clients qui n’ont pas de commande
-- ========================================================

DELETE FROM customers
WHERE id NOT IN (
    SELECT DISTINCT customer_id
    FROM orders
);

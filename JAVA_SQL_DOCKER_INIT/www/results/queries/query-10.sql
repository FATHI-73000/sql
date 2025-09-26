SELECT
    c.first_name,
    c.last_name,
    COUNT(o.id) AS nb_commandes
FROM customers AS c
LEFT JOIN orders AS o ON o.customer_id = c.id
GROUP BY c.id, c.first_name, c.last_name;

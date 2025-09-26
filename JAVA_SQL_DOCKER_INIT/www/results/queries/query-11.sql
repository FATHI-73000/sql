SELECT
    c.first_name AS prenom,
    c.last_name AS nom,
    COALESCE(SUM(op.quantity * p.price), 0) AS total
FROM customers AS c
LEFT JOIN orders AS o ON o.customer_id = c.id
LEFT JOIN order_product AS op ON o.id = op.order_id
LEFT JOIN products AS p ON op.product_id = p.id
GROUP BY c.id, c.first_name, c.last_name
ORDER BY c.first_name, c.last_name;

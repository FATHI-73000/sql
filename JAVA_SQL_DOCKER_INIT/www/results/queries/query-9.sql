SELECT o.number, SUM(op.quantity * p.price) AS total
FROM orders AS o
JOIN customers AS c ON o.customer_id = c.id
JOIN order_product AS op ON o.id = op.order_id
JOIN products AS p ON op.product_id = p.id
WHERE c.first_name = 'Charlize'
GROUP BY o.id, o.number;

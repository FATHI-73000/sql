SELECT o.number, SUM(op.quantity * p.price) AS total
FROM orders AS o
JOIN order_product AS op ON o.id = op.order_id
JOIN products AS p ON op.product_id = p.id
GROUP BY o.id, o.number
HAVING total BETWEEN 100 AND 550;

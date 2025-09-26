SELECT SUM(op.quantity * p.price) AS total_today
FROM orders AS o
JOIN order_product AS op ON o.id = op.order_id
JOIN products AS p ON op.product_id = p.id
WHERE DATE(o.date) = CURRENT_DATE;

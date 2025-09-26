SELECT p.name, op.quantity, p.price
FROM order_product AS op
JOIN products AS p ON op.product_id = p.id
WHERE op.order_id = 1;

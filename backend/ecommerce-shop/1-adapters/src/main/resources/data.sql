-- Vorhandene Daten löschen
DELETE FROM carts_products;
DELETE FROM carts;
DELETE FROM orders_products;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM categories;
DELETE FROM users;

-- Neue Testdaten einfügen

-- Benutzer einfügen
INSERT INTO users (id, username, email, street, city, postal_code, country)
VALUES 
  ('f5eae2f4-ffae-4f8e-9c5f-1f5c2f8e9f1f', 'jae_doe', 'jane@example.com', 'Second Street 456', 'Los Angeles', '90001', 'USA');

-- Kategorien einfügen
INSERT INTO categories (id, name) 
VALUES 
  ('c1a6e2b0-ffae-4f3a-a43e-1119ba66046e', 'Laptops'),
  ('c2b7c9d2-ffae-4f3a-bc7d-8f5a1f7d8e1e', 'Smartphones'),
  ('c3d8d3d2-ffae-4f3a-8e5b-1029ba66046e', 'Audio');

-- Produkte mit Kategorien verknüpfen
INSERT INTO products (id, name, description, price,image_url, category_id)
VALUES 
  ('b1a6a3b0-fd3a-4f3a-a43e-1029ba66046e', 'Laptop', 'High-End Gaming Laptop', 1499.99,'https://picsum.photos/200/300', 'c1a6e2b0-ffae-4f3a-a43e-1119ba66046e'),
  ('c2b7b8a1-ab5c-4c5e-9f5b-6f2bcf6d7cc2', 'Smartphone', 'Flagship Smartphone', 999.99,'https://picsum.photos/200/300', 'c2b7c9d2-ffae-4f3a-bc7d-8f5a1f7d8e1e'),
  ('d3c8c9d2-cf7d-4e6e-8b3d-8f5a1f7d8e1e', 'Wireless Headphones', 'Noise Cancelling Headphones', 299.99,'https://picsum.photos/200/300', 'c3d8d3d2-ffae-4f3a-8e5b-1029ba66046e');

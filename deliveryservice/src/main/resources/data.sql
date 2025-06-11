-- Correct for Delivery_Status table
INSERT INTO Delivery_Status (delivery_status) VALUES ('ASSIGNED');
INSERT INTO Delivery_Status (delivery_status) VALUES ('OUT_FOR_DELIVERY');
INSERT INTO Delivery_Status (delivery_status) VALUES ('DELIVERED');

-- Correct for Order_Status table
INSERT INTO Order_Status (order_status) VALUES ('PLACED');
INSERT INTO Order_Status (order_status) VALUES ('IN_TRANSIT');
INSERT INTO Order_Status (order_status) VALUES ('DELIVERED');
INSERT INTO Order_Status (order_status) VALUES ('CANCELLED');

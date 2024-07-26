
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('AAPL', 'Apple Inc.', 150.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('GOOGL', 'Alphabet Inc.', 2800.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('AMZN', 'Amazon.com, Inc.', 3400.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('MSFT', 'Microsoft Corporation', 300.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('TSLA', 'Tesla, Inc.', 700.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('NFLX', 'Netflix, Inc.', 500.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('ORCL', 'Oracle Corporation', 80.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('INTC', 'Intel Corporation', 60.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('CSCO', 'Cisco Systems, Inc.', 55.00, CURRENT_TIMESTAMP);
INSERT INTO stock (name, description, current_price, last_updated) VALUES ('IBM', 'International Business Machines Corporation', 130.00, CURRENT_TIMESTAMP);


INSERT INTO stock_exchange (name, description, live_in_market) VALUES ('NYSE', 'New York Stock Exchange', TRUE);
INSERT INTO stock_exchange (name, description, live_in_market) VALUES ('NASDAQ', 'NASDAQ Stock Market', TRUE);
INSERT INTO stock_exchange (name, description, live_in_market) VALUES ('LSE', 'London Stock Exchange', TRUE);
INSERT INTO stock_exchange (name, description, live_in_market) VALUES ('JPX', 'Japan Exchange Group', FALSE);
INSERT INTO stock_exchange (name, description, live_in_market) VALUES ('SSE', 'Shanghai Stock Exchange', FALSE);


INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 1);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 2);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 3);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 4);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 5);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (1, 6);


INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 1);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 2);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 3);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 4);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 5);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (2, 6);


INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 1);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 2);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 3);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 4);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 5);
INSERT INTO stock_exchange_stock (stock_exchange_id, stock_id) VALUES (3, 6);


UPDATE stock_exchange SET live_in_market = FALSE WHERE id IN (
    SELECT stock_exchange_id
    FROM stock_exchange_stock
    GROUP BY stock_exchange_id
    HAVING COUNT(stock_id) < 5
);
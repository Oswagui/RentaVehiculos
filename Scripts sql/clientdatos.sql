SELECT * FROM alquilerdevehiculos.cliente;
INSERT INTO cliente
VALUES(300000001,0,'1600355986','Valentina Bravo','095683695','Avenida Rio verde y Plaza Ventura 564','Valentina'),
	  (300000002,1,'0968631568001','Televisa','098956348','Blvd. Adolfo López Mateos No. 2551 Col. Lomas de San Ángel','Televisa SA');
      
delete from cliente
where id_cliente=300000001;
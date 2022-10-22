create table public.admins (
                               id integer primary key not null default nextval('admins_id_seq'::regclass),
                               email character varying(255),
                               password character varying(255),
                               deleted boolean default false
);

create table public.cards (
                              id integer primary key not null default nextval('cards_id_seq'::regclass),
                              cvv character varying(255),
                              expiration_date character varying(255),
                              number character varying(255),
                              owner_id integer not null,
                              foreign key (owner_id) references public.clients (id)
                                  match simple on update no action on delete no action
);

create table public.clients (
                                id integer primary key not null default nextval('clients_id_seq'::regclass),
                                name character varying(255),
                                surname character varying(255),
                                password character varying(255),
                                phone_number character varying(255),
                                deleted boolean default false,
                                email character varying(255),
                                promo_id integer,
                                foreign key (promo_id) references public.promos (id)
                                    match simple on update no action on delete no action
);

create table public.clients_cards (
                                      client_id integer not null,
                                      cards_id integer not null,
                                      primary key (client_id, cards_id),
                                      foreign key (client_id) references public.clients (id)
                                          match simple on update no action on delete no action,
                                      foreign key (cards_id) references public.cards (id)
                                          match simple on update no action on delete no action
);
create unique index uk_ns2ubjaxbojixsq0ah3pbqk5e on clients_cards using btree (cards_id);

create table public.goods (
                              id integer primary key not null default nextval('goods_id_seq'::regclass),
                              title character varying(255),
                              description character varying(255),
                              price double precision,
                              article character varying(255),
                              deleted boolean default false,
                              amount integer,
                              added_date_time numeric,
                              comment character varying(128)
);

create table public.order_requests (
                                       id integer primary key not null default nextval('order_requests_id_seq'::regclass),
                                       article character varying(255),
                                       amount character varying(255),
                                       client_email character varying(255),
                                       delivery_method character varying(255),
                                       payment_method character varying(255),
                                       deleted boolean default false,
                                       status character varying(255)
);

create table public.promos (
                               id integer primary key not null default nextval('promos_id_seq'::regclass),
                               value character varying(255),
                               is_active boolean not null
);


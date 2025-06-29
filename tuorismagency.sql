PGDMP  *                    |            tourismagency    15.6    16.2 "    &           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            '           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            (           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            )           1262    16398    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    16408    hotel    TABLE     4  CREATE TABLE public.hotel (
    hotel_id bigint NOT NULL,
    hotel_name text NOT NULL,
    hotel_city text NOT NULL,
    hotel_district text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star bigint NOT NULL,
    hotel_pension_type text NOT NULL,
    hotel_park boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16407    otel_otel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.otel_otel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16424    pension_type    TABLE     �   CREATE TABLE public.pension_type (
    pension_id bigint NOT NULL,
    pension_type text NOT NULL,
    pension_hotel_id bigint NOT NULL
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    16423    pension_type_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension_type ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_type_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    16440    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id bigint NOT NULL,
    reservation_room_id bigint NOT NULL,
    customer_name text NOT NULL,
    customer_citizen_id text NOT NULL,
    customer_address text,
    customer_mail text NOT NULL,
    customer_mpno text NOT NULL,
    reservation_note text,
    check_indate date NOT NULL,
    check_outdate date NOT NULL,
    total_price double precision,
    guest_count bigint NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    16439    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225            �            1259    16432    room    TABLE     7  CREATE TABLE public.room (
    room_id bigint NOT NULL,
    room_hotel_id bigint NOT NULL,
    room_season_id bigint NOT NULL,
    room_pension_id bigint NOT NULL,
    room_type text NOT NULL,
    room_bed_number bigint NOT NULL,
    room_size bigint NOT NULL,
    room_tv boolean NOT NULL,
    room_minibar boolean NOT NULL,
    room_game_console boolean NOT NULL,
    room_chest boolean NOT NULL,
    room_projection boolean NOT NULL,
    room_stock bigint NOT NULL,
    room_adult_price double precision NOT NULL,
    room_child_price double precision NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16431    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    16416    season    TABLE     �   CREATE TABLE public.season (
    season_id bigint NOT NULL,
    season_hotel_id bigint NOT NULL,
    season_start date NOT NULL,
    season_finish date NOT NULL,
    season_name text
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16415    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16400    user    TABLE     �   CREATE TABLE public."user" (
    user_id bigint NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16399    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215                      0    16408    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_district, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_pension_type, hotel_park, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_room_service) FROM stdin;
    public          postgres    false    217   #+                 0    16424    pension_type 
   TABLE DATA           R   COPY public.pension_type (pension_id, pension_type, pension_hotel_id) FROM stdin;
    public          postgres    false    221   ^-       #          0    16440    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, reservation_room_id, customer_name, customer_citizen_id, customer_address, customer_mail, customer_mpno, reservation_note, check_indate, check_outdate, total_price, guest_count) FROM stdin;
    public          postgres    false    225   .       !          0    16432    room 
   TABLE DATA           �   COPY public.room (room_id, room_hotel_id, room_season_id, room_pension_id, room_type, room_bed_number, room_size, room_tv, room_minibar, room_game_console, room_chest, room_projection, room_stock, room_adult_price, room_child_price) FROM stdin;
    public          postgres    false    223   �.                 0    16416    season 
   TABLE DATA           f   COPY public.season (season_id, season_hotel_id, season_start, season_finish, season_name) FROM stdin;
    public          postgres    false    219   �/                 0    16400    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    215   0       *           0    0    otel_otel_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.otel_otel_id_seq', 10, true);
          public          postgres    false    216            +           0    0    pension_type_pension_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.pension_type_pension_id_seq', 14, true);
          public          postgres    false    220            ,           0    0    reservation_reservation_id_seq    SEQUENCE SET     M   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 19, true);
          public          postgres    false    224            -           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 13, true);
          public          postgres    false    222            .           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 11, true);
          public          postgres    false    218            /           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 7, true);
          public          postgres    false    214            �           2606    16414    hotel otel_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT otel_pkey PRIMARY KEY (hotel_id);
 9   ALTER TABLE ONLY public.hotel DROP CONSTRAINT otel_pkey;
       public            postgres    false    217            �           2606    16430    pension_type pension_type_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (pension_id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    221            �           2606    16446    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    225            �           2606    16438    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    223            �           2606    16422    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219                       2606    16406    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    215               +  x�]�Kn�0���S���%K��q����H�	��آk�PT �=F�A��d���{���W1�����sÂ��*o��/�]�i!�0o����A5wk���a�<q!n��a�X
<���ṝƌ1���M�NgԫJ������ʖ��4�l'�pH���r��5U�X���ZL���3h��!���)��%�k
YqA�ZT20�_�U�m4�l�e��>�8c�q�g�c�7�'�S@��3K�xmc��7�����B
�3[��ę��/De��E}���Rh����]ར��
/eH��<BO'l�I>�X/6��?�Ꜵ��Ȼ@��c�Y�RH�D�3��u%o��1�a^/�c��Y��,k?�)���V��.	K�D8?�&���o*Bc�<I��騒d�E|2��4Is��k]��(^�i�y?k�j�uu'F�#Yv�}���gƫ�+>�x.V��Rl�C}I��&I��+T��Q�֒�.OG6�q��q@�����{8l%����½���o'��d���oZ�M�]+����<~>�ۊ��$�         �   x�m�;!���c8_�a4j�1��!��Ɵ/4�b9;3��#��:���)G{7���ϡ�m�IǰV4H�BU�?����`�uP1�V�h�7.Z�J�5/������Ǜ�|����Q�/�ja�����Y��">fBC      #   �   x�]��� Eח�a�ҝW~A7�}&`��qh�&6S8�� �p���A�4�̂y��sx�R���&X!�X�_p���:���Q?����k\F�8Ғ���k�Y�M���Odd8�g�#s�j���|����R�Oe�w#�+h>N�^�;����X�Ԣ����2�| ��C6      !   �   x�u�K
�0Dף�Y��B�Ͷ۴�Jr���%�Ȍ�%D*c���k�-��X�|O������a�n�|�mZG��n>�Y*��0œN�]�C����g��\�F$��sz�G�B�g�轁wuH���=ұٸ��9���}��)9�c��Ѭ�(5F.��98��ED�
\W�         s   x�}�K
�0���.�$U�Pԅ�x�sXQ��.�7�(�*�k�r��׸l���T�̃ʀ��y��Ok>�:$@i4�xF���|\?ya�
�� �̏� ���>&         6   x�3�LL����4426�0��8�KS�K B��9����\f��9�9h�1z\\\ ��     
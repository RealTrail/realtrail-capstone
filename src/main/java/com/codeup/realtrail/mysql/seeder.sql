USE realtrail_db;

INSERT INTO users(id, city, email, first_name, gender, last_name, password, phone_number, profile_image_url, state, username)
VALUES
    (1, 'Universal City', 'shanshan@shan.com', 'Shanshan', 'F', 'Su', '$2a$10$IjGrLooGQ0EiT6OS4K96Ou6/7m7d7ZIYcIIZZS3FX0yTVDEurzrIK','210-123-4567', 'https://cdn.filestackcontent.com/072ckNiNS8iImzI53sMY', 'TX', 'Shan'),
    (2, 'San Antonio', 'kjar@gmail.com', 'Karla', 'F', 'Jara', '$2a$10$bzg3Tcw4Q/srFDwlmUYEYeJrT5CFZvCUsQMXiZO3QLTB7Icj3kwRa', '123-435-0743', 'https://cdn.filestackcontent.com/PBwhSh4nSAGwF1Tlcr2U', 'TX', 'kjar007'),
    (3, 'San Antonio', 'salimkk780@gmail.com', 'Salim', 'M', 'Ahmedabadi', '$2a$10$Pr1mk/AzGVKXBr6retEEYuVNOtRqnajAIgiCB0iuEDmb0mzDHetfi', '378-987-1735', 'https://cdn.filestackcontent.com/odZiQGMQXiObAbybdNrO', 'TX', 'salimk785'),
    (4, 'San Antonio', 'victor.g.hernandez87@gmail.com', 'Victor', 'M', 'Hernandez', 'encrypted_password', '988-198-2928', 'https://ca.slack-edge.com/T029BRGN0-U01KK05TXP0-99b4452cba22-512', 'TX', 'G-man'),
    (5, 'Killeen', 'emmanuel.stephen68@gmail.com', 'Emmanuel', 'M', 'Stephen', '$2a$10$FQzRQY.zOlvbL4EGwVTR5.P3i8S5as30fVVg9ApYQHD3r3sn2kZDO', '943-294-9923', 'https://avatars.githubusercontent.com/u/59237112?v=4', 'TX', 'manii1'),
   (7, 'Princeton', 'cat123@cat.com', 'Kitty', 'M', 'Cute',  '$2a$10$Vm0gJalOoURhce98oeYhYOhKZvD8F0/3F/Vgx4dKj714RJuQRyetC', '993-156-9231', 'https://cdn.filestackcontent.com/luOfEwtiThetHDkagICG', 'NJ', 'cat'),
   (8, null, 'momo80@hotmail.com', null, null, null, '$2a$10$VH4m1vAKLQ7fIexUbx5CUOY3eTuvSWDbVL3c.z5DTsql3vqYSKp6G', null, null, null, 'mono');

INSERT INTO interests(interest) VALUES
   ('Walking/Running'),
   ('Hiking'),
   ('Mountain Biking'),
   ('Road Biking');

INSERT INTO trails(id, name, difficulty_level, rating, length, elevation, type, longitude, latitude, trail_details)
VALUES
       (1, 'Joe Johnston Route',
       'Moderate',
       4.4,
       9.3,
       652,
       'Loop',
        -98.7673,
        29.5745,
       'Government Canyon State Natural Area charges an entrance fee per adult, while children 12 and under are free. Reservations can be made at https://tpwd.texas.gov/state-parks/government-canyon. Trails may close due to poor conditions or weather. Check out their Facebook accounts (https://www.facebook.com/TPWDGCSNA/) for the latest trail reports, or call the park. Nice Point to Point trail. Dinosaur footprints recently found has added to the traffic. Rocky, mild elevations pretty canyon where the footprints were discovered.'),
       (2, 'Savannah Loop',
        'Easy',
        4.4,
        2.3,
        62,
        'Loop',
        -98.7543,
        29.5450,
        'Savannah Trail Loop is a 2.3 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips and is accessible year-round. Dogs are also able to use this trail but must be kept on leash.'),
       (3, 'Lytle''s Loop',
        'Easy',
        4.2,
        4.4,
        131,
        'Loop',
        -98.7494,
        29.5459,
        'Lytle''s Loop Trail is a 4.4 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail offers a number of activity options and is best used from February until October. Dogs are also able to use this trail but must be kept on leash.'),
       (4, 'Wildcat Canyon Trail',
        'Easy',
        4.4,
        5.4,
        301,
        'Out and back',
        -98.7550,
        29.5709,
        'Government Canyon Wildcat Canyon Trail is a 5.4 mile moderately trafficked out and back trail located near San Antonio, Texas that features beautiful wild flowers and is rated as moderate. The trail offers a number of activity options and is accessible year-round.'),
       (5, 'Sendero Balcones',
        'Easy',
        2.8,
        4.5,
        331,
        'Point to point',
        -98.7449,
        29.5774,
        'Stretching across Government Canyon''s backcountry, Sendero Balcones cuts a path along the top of the Balcones Escarpment. This rough, rock-strewn trail requires constant attention to pathfinding and can be very mentally tiring.

The track splits off from Recharge Trail at a signed map and a trail marker just east of Bluff Spurs. It is a rocky, moderately technical singletrack, at times widening to doubletrack. The rocks come in varying sizes. While they don''t require any climbing, they often create hazardous footing throughout Sendero Balcones'' four-plus miles, and therein lies most of the challenge of this route. It requires an eyes-down approach to hiking and a slower pace than other area trails. On the up side, nearly the entire trail is covered by tree canopy, providing plenty of shade from the scorching Texas sun.

The path makes a steady, low-grade climb up to the top of one of the Balcones'' many hills over its first mile. The second entry to Bluff Spurs is found about a half mile in, at another map/marker intersection. From here, it''s a remote-feeling mile and a half to the next intersection, with a few brief smooth segments breaking up the constant rock hopping and dodging. Thanks to the thick tree cover, the view doesn''t change much, but the plant life makes the air fragrant and cool.'),
       (6, 'Black Hill Loop',
        'Easy',
        4.3,
        5.0,
        283,
        'Point to point',
        -98.7453,
        29.5992,
        'The longest trail in Government Canyon, secluded Black Hill Loop circles the Protected Habitat Area. The northern end starts at a large gate close to where Sendero Balcones turns west. Singletrack and considerably smoother, at least at the beginning, the route heads off to the northeast on a mostly flat course. Some grass overgrowth helps cushion the path even further, making it a welcome break from the rocky trails that connect to it.

A greater variety of plant life is present along Black Hill Loop. Look for different types of cacti and some very large sotol plants. Black Hill is also much more open to the sky, with some nice large clearings to break up the forested sections. A few rocky sections add some changes, but they are not technical, making for an easy and fast first two miles.

At the mile and a half point, where the path turns back towards the trailhead, there is a huge cactus field to the left of the trail. Cave Creek Trail is at the southern end of a large clearing just after this section. There are only brown markers to show where the trail splits off, so keep an eye out for a dirt singletrack branching to the right.

As Black Hill nears the two-mile point, a neighborhood is visible to the left. Not long after, the trail begins a quick descent followed by a half-mile climb, twisting frequently as it goes. The route is much more challenging through these sections, requiring some careful footwork to pick a clean line through the rocks which have started to appear. The more technical character of the trail will continue until the end, also throwing in some roots growing across the path which can catch an unsuspecting hiker''s foot.'),
       (7, 'Twin Oaks Trail',
        'Easy',
        3.0,
        2.7,
        288,
        'Point to point',
        -98.7542,
        29.5801,
        'The Twin Oaks Trail begins a little after reaching the two mile point on Joe Johnston Route. Look for the bench/trail map/marker combination present at most of the park''s major intersections. The path begins as a fairly wide singletrack, taking a frequently winding path through tree canopy. The live oaks that give this trail its name are a staple of the Texas Hill Country, and they provide ample shade from the sun throughout the route.

As Twin Oaks climbs, it becomes a little more technical than the Joe Johnston Route. Smooth and rocky sections alternate, and while there are a few tricky areas requiring some pathfinding through debris, they are usually short and not very troublesome. Users familiar with other Hill Country routes will find Twin Oaks to be familiar territory; this is a "stereotypical" central Texas trail.

Around a mile and a quarter in, the path passes its namesake twin oaks, seen off the trail to the right. A bench facing the trees here provides a rest opportunity. Continue onward to the northeast. The next three quarters of a mile is fairly flat and easy to manage.

Twin Oaks crosses Sendero Balcones just after the two mile mark. A large sign is posted at the intersection. The remaining portion, a little over a half mile in length, is within the Protected Habitat Area and is the southernmost entry point. This region of the State Natural Area is restricted to pedestrians only and is open from September to February. A wooden barrier will be placed across the singletrack during closures.

The final portion is a counterclockwise circle, with a slow climb up and back down to the finish. The tree canopy opens up a few times along the way. The increased sunlight encourages grass growth, which can obscure underlying rocks at times, so use caution. Twin Oaks ends at Black Hill Loop. From here, hikers can turn right to visit the furthest reaches of the park, or left to return to Sendero Balcones.'),
        (8, 'Bear Grass Trail',
        'Moderate',
        4.0,
        1.8,
        150,
        'Loop',
         -98.6262,
         29.6230,
        'Nice little trail. Narrow in some areas. Clearly marked though.

        Easy walk thru Crownridge Canyon Natural Area. Several plant reclamation areas throughout the park with small informative plaques.'),
       (9, 'Red Oak Trail',
        'Easy',
        4.2,
        1.3,
        72,
        'Loop',
        -98.6300,
        29.6199,
        'Nice little trail. Narrow in some areas. Clearly marked though.

        You''ll see the marker for Bear Grass at the top of the Red Oak trail. It goes off the pavement into natural terrain. Steep in areas and muddy after rain.'),
       (10, 'Apache Creek Greenway Trail',
        'Moderate',
        3.5,
        12.3,
        259,
        'Cycleway',
        -98.5186,
        29.4133,
        'Apache Creek Greenway is a 12.3 mile out and back trail located near San Antonio, Texas. The trail is rated as moderate and offers a number of activity options.'),
       (11, 'Medina River Greenway Trail',
        'Easy',
        4.4,
        13.3,
        413,
        'Cycleway',
        -98.5141,
        29.2626,
        'This is a nice long greenway trail that starts at the golf course and has a varied terrain - lakes, fields, and the river.

Accessibility: There are 2 designated accessible spaces in the paved parking lot off of Clubhouse Boulevard at the east end of the trail. Both of them are van-accessible with a striped access aisle. The trail surface is cement, smooth, and typically at least 6 feet wide. The most accessible portion of the trail is the first 3.5 miles out. The estimated grade is mostly gentle (5% or less) except for very steep (over 12%) uphill and downhill sections at about 4.0 through 12.0 miles when going west.  Wheelchair/mobility equipment or stroller users may need assistance in the steeper sections or to avoid them for safety. There are benches along the route for resting.'),
       (12, 'Salado Creek Greenway Trail',
        'Easy',
        4.6,
        22.7,
        354,
        'Cycleway',
        -98.4914,
        29.5549,
        'Salado Creek Greenway offers 19 miles of paved trail along San Antonio''s east side. Although the trail is still under development, its three completed segments (listed below, north to south) offer connections to many parks and neighborhoods.
Segment 1: Huebner Road to West Avenue

This northernmost segment is about 5 miles long and one of the trail''s most scenic. The pathway traces the Salado Creek through bluffs and hardwood forest. A highlight of the route is Voelcker farmstead, an archaeological site and historic set of buildings situated in Phil Hardberger Park. Named after a former San Antonio mayor, the park is an urban green oasis with native grasses and heritage oaks.

The trail continues to another worthwhile attraction, Walker Ranch Historic Landmark Park, which offers beautiful natural landscapes, picnic areas, a large pavilion, restrooms, and a playground.

To continue east on the greenway, passing Walker Ranch Park, take a left at the fork as you approach the park. This will keep you on the northern edge of the historical park. There will be another fork as you approach the main parking area of Walker Ranch. Again, stay to the left at the fork. This will keep you on the Salado Creek Greenway and take you under West Avenue and across North Loop Road to the temporary end (as of Sept. 2014) of this segment at US Highway 281.

Segment 2: McAllister Park to James Park

This middle segment is about 6.5 miles long. Its northern end offers a hilltop view of the city skyline and airport. The path traverses over a scenic wetland area with the Morningstar Boardwalk, named in memory of Army Staff Sergeant Christopher Morningstar, and continues through dense woodlands.

About two miles south of McAllister Park, a spur will take you to Lady Bird Johnson Park, a community favorite with a swimming pool, skate park, softball and soccer fields, and basketball courts. Farther south the trails runs through Los Patios, a shopping and dining area, before heading under Loop 410.

On the other side of the busy roadway, the pathway offers a well-shaded retreat under towering pecan and oak trees as it winds through Robert L.B. Tobin Park. The park is a terrific spot for bird-watchers; you may see warblers, buntings, hawks, owls, and many other types of birds. It also offers unique faux bois art in its signage, picnic tables, and benches. The trail ends at John James Park, named for a surveyor who helped establish the city''s boundaries in 1846. The park offers athletic fields, a playground, and restrooms.

Segment 3: Jack White Park to South Side Lions Park

Stretching about 7.5 miles, this is the trail’s longest and southernmost section. It begins in Jack White Park on the north side of I-35 and winds south through Pletz County Park, Martin Luther King Park, Comanche Park, and South Side Lions Park, offering the traveler many amenities and picturesque views of the forest and creek.'),
       (13, 'Main Loop',
        'Moderate',
        4.6,
        2.6,
        354,
        'Loop',
        -98.6310,
        29.6394,
        'Friedrich Wilderness Park offers approximately 10 miles of hiking trails with varying degrees of difficulty. It is home for rare birds, terrestrial orchids, steep hills and deep canyons. It is internationally known for bird watching. Perched on the edge of the Balcones Escarpment, Friedrich is a nesting site for two federally listed endangered species of birds: the Black-capped Vireo and the Golden-cheeked Warbler.

        Restrooms and water fountains are found at the entrance to the park. Visitors are encouraged to bring water bottles for longer hikes in warm weather. In order to protect this wilderness area, it is important to observe the following rules:

        No fires,
        no smoking on trails,
         Stay on designated trails.
        Pedestrians only please; roller blades, skateboards, scooters, bicycles, etc., are not allowed.
        To protect the endangered species, pets, including dogs, are not allowed.'),
       (14, 'Restoration Way Trail',
        'Easy',
        4.5,
        5.1,
        511,
        'Loop',
        -98.6403,
        29.6347,
        'Restoration Way Trail Loop is a 5.1 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips. SEASONAL CLOSURE: This area is subject to seasonal closure due to weather conditions. For more information, please visit https://www.sanantonio.gov/ParksAndRec/Parks-Facilities/All-Parks-Facilities/Parks-Facilities-Details/ArtMID/14820/ArticleID/2649/Friedrich-Wilderness-Park'),
       (15, 'Blue Loop',
        'Moderate',
        4.3,
        6.7,
        236,
        'Loop',
        -98.4504,
        29.5565,
        'Great park for mountain biking.  The blue loop is fairly easy; however, it can get tough during the summer months when temperatures soar and there is little shade.  Plan to start early to beat the heat and the crowd.

        There are bathrooms by the parking lot and throughout the park.

        Accessibility: There is a combination of paved and dirt areas on this trail, and the dirt sections may not be accessible for mobility equipment or strollers. The estimated grade is moderately steep, up to 8%. The paved sections are typically at least three feet wide, but the dirt sections may be as narrow as 1.5 feet wide, may be rocky, and may have a cross-slope of more than 2%.'),
       (16, 'Baseball Field Trail',
        'Easy',
        4.4,
        2.4,
        32,
        'Loop',
        -98.4531,
        29.5570,
        'This is a short but enjoyable gentle and paved multi-use path through wooded areas. For a longer route, you can continue on the blue trail but it is reportedly rockier.

        Accessibility: There are 3 designated accessible spaces in the paved parking lot off of Turke Roost at the north end of the trail. All of them are van-accessible with striped access aisles. The trail surface is paved asphalt with bumps and cracks. It is typically at least 5 feet wide. The majority of the trail is estimated to be in the gentle (3% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users. '),
       (17, 'Red Trail',
        'Easy',
        3.5,
        1.9,
        47,
        'Point to point',
        -98.4533,
        29.5550,
        'McAllister Park Red trail is connected to Blue trail and you can go to some other trails form here. Just be aware of mountain bikers.'),
       (18, 'Olmos Basin Loop Trail',
        'Easy',
        4.0,
        2.4,
        196,
        'Loop',
        -98.4803,
        29.4838,
        'Olmos Basin Trail is good for all skill levels. The trail is primarily used for running and nature trips. Dogs are also able to use this trail but must be kept on leash.'),
       (19, 'Acequia Trail',
        'Easy',
        4.6,
        15.1,
        413,
        'Cycleway',
        -98.4579,
        29.3373,
        'This trail is also called San Antoino Mission Trail guiding people from each of the missions in San Antonio.

        The word mission usually connotes religion. It''s true that San Antonio''s missions revolved around religion and faith, but it doesn''t describe the importance or full scope for any of them. Though intentionally created to spread the Catholic faith, missions are not churches. The church is merely one building among several in any mission. Each complex served as a separate, unique community.

All four churches (Mission Concepcin''s, Mission San Jose''s, Mission San Juan Capistrano''s, and Mission Espada''s) have active Catholic congregations. Though the congregations haven''t been continuously active since the 1700s, many of today''s members are direct descendants of the Indians who built the churches.

In addition to the families, the missions served as the foundation for much of what you’’ll see in Texas today. The architecture, food, culture, textiles, and more all began in the missions in one way or another. Each is different and several served as important sites during the Texas Revolution.

When you visit the missions, be careful. These are historic structures and the masonry may not be able to hold your weight (such as if you decide to try and walk atop a retaining wall). Also, some structures may not be accessible due to restoration work or otherwise.'),
       (20, 'River Walk',
        'Easy',
        4.4,
        15,
        141,
        'Loop',
        -98.4886,
        29.4166,
        'History, River, Plaza, Cathedral, Tower, Alamo, parks, Rivercenter, King William

        This is a 15 mile long series of walkways that run along the San Antonio river, with shopping, dining and other attractions along the route. Walk on sidewalks at river-level & on west side of the River. The Walk ends at a wide stairs going up to old town square, Bexar County Courthouse, & San Fernando Cathedral. South on Soledad St. to Nueva St. & re-enter River Walk at the dam. Go to end & cross Guenther St. bridge. Pass thru King William homes & re-enter River Walk on east bank. Go to La Villita River Theatre. Through La Villita, Hemisfair Plaza, to Inst. of Texan Cultures & Tower. North to Commerce St., north on Alamo St. to The Alamo. Cross Alamo St. into River Center. Re-enter River Walk & north to footbridge. Right & back to bridge at dam. Right & north along east bank of river. Leave River Walk at Travis St. & follow to Travis Park. Diagonally across Park & follow Jefferson St. by war memorials & auditorium to Lexington. Left back to Sheraton'),
       (21, 'Leon Creek Greenway Trail',
        'Easy',
        4.4,
        12.3,
        196,
        'Point to point',
        -98.6242,
        29.5312,
        'Leon Creek Greenway Trail is a 12.3 mile heavily trafficked point-to-point trail located near Shavano Park, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for walking, running, and road biking and is accessible year-round. Dogs are also able to use this trail but must be kept on leash.

        The route shown on this page is the flat paved path along the creek. There are numerous dirt mountain biking and hiking trails off of this paved trail. Electric motor vehicles are reportedly not allowed on this trail, and you may get ticketed if you use them.

Accessibility: There are 2 designated accessible spaces in the paved parking lot off of North Loop 1604 West at the north end of the trail. Both of them are van-accessible with striped access aisles. There are 4 designated accessible spaces in the paved parking lot off of North Western at the south end of the trail. All of them are van-accessible with striped access aisles.

The trail surface is paved concrete and smooth. It is typically at least 8 feet wide. The majority of the trail is estimated to be in the mostly gentle (5% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users. ');

INSERT INTO picture_urls (id, picture_url, trail_id) VALUES (1, ('https://cdn-assets.alltrails.com/uploads/photo/image/37482612/large_bb9f26b14665214374ba39969507ad83.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35738330/large_89caf79acbd5b0d810aa1940e1d190cc.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35125362/large_fbd07eb932ed121a91fc9bb74458b500.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34657293/large_4b044d1af6640750c626933d4044e289.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37552839/large_595d3f7308b56ede3fdee942bb1e02f8.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/33281343/extra_large_40d83b0855437bdb50825f45d36ad684.jpg'), 1),
                                                            (2, ('https://cdn-assets.alltrails.com/uploads/photo/image/10798896/large_bb97842cfcdf28ca7eada3240b23395b.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37483071/large_559d65b261985d1eccd38fdfeb29dcb5.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37482993/large_4dcd70a2f4d0df1ea487a3337feab9e7.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37482958/large_83f83fb3bec89cd1c3124349beb2205b.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37482800/large_57fa328517906a317af81fe94fa5a8bc.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37482691/large_91dc862bc6b48d47adf1e390532c0462.jpg'), 2),
                                                            (3, ('https://cdn-assets.alltrails.com/uploads/photo/image/31175870/large_71c4e37ab8d324bd1c2f033a6f9f359e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34545240/large_c0166f0e890d58e5a9df9d78a0e9c26e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37482675/large_487c3d4a70679b4e7ceae5b728b96507.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/29572831/large_554017da49add74704ef7fe960b923b3.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/29572830/large_c3311aaf74a967ff0c13bdd6eb2e3a0e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/31104488/large_75ee91b15fe2cc2c7795567458bf8a37.jpg'), 3),
                                                            (4, ('https://cdn-assets.alltrails.com/uploads/photo/image/37941870/large_f0dc9eb31414b26c74d912f43d972d1c.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37807449/large_216ca0ac40bf937542fea44c13154857.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37807447/large_34efe0eeb022beaf6b6c6767f810bce2.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37807449/large_216ca0ac40bf937542fea44c13154857.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37300150/large_9d5ffb45dee48555fba6f287890feeb3.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36887181/large_c563ecd6eadec8e4f3cc8d978ae1b066.jpg'), 4),
                                                            (5, ('https://cdn-assets.alltrails.com/uploads/photo/image/37349392/large_2ebd740186ff15182b7d10edf6917b2a.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35141136/large_12fc5c02bd8b9130192036ae636764d9.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34995080/large_7e7bb8b99fc12b0c2e3c0edba538e932.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35083404/large_a0a2c35f219428459cd3675f517ef7bf.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/33383332/large_35021abfb34d6d90e6fda78d8a67f8e2.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/32709673/large_269f1add019428d69c6c0321e0e37d08.jpg'), 5),
                                                            (6, ('https://cdn-assets.alltrails.com/uploads/photo/image/37159360/large_19ffa344127bb824abb60720dda7d058.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37159214/large_5c452c2b0776b28a6e798ee81c86e079.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37159358/large_0bd579f3db4c25f2af7d3151cb97cd40.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36543988/large_a6b06a586d8956e9cce4e3ac505b6058.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35013855/large_039301318f3981463598dc35f99a1b15.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34368678/large_5dc53b1c6aa0dfb025f307df8333db68.jpg'), 6),
                                                            (7, ('https://cdn-assets.alltrails.com/uploads/photo/image/35838918/large_280b46b5bac5dbf21a54593e2a23ac4e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36776383/large_65dae1eae5fc3e3b06b8ef6deaca6c6c.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35510523/large_f0bb4b0e5fbc6445cb2d29bf4de17c5b.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35172710/large_afbb9a72c6d0d8cde38e8db9c69cb3c7.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34131180/large_b5fca628253531760dcaf1dd6b930b49.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/30621393/large_6aa1f68cb857bd8d59bfc72222c7c3da.jpg'), 7),
                                                            (8, ('https://cdn-assets.alltrails.com/uploads/photo/image/35689293/large_23bc927a011169c6da2e4cb9af8541b4.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35689271/large_0b1f202f9a9f1b8ce99cc1468c07e101.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34172195/large_7ec4dbc248b95d29ba9ad36b2c1c0842.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34172193/large_0e56dba8f74916630dc5ef8a9a3ed5a1.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/33257885/large_4bc5b6e38bb921e232f49cff4deafebd.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/29431465/large_e20d17936c0f8a37211f931632632d31.jpg'), 8),
                                                            (9, ('https://cdn-assets.alltrails.com/uploads/photo/image/28402654/large_b9f92ea0e4211d130e387f02a9d57262.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/28233937/large_1ec0555cfb2c64b60b21da65280ea312.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/28402622/large_585948ea5ebe192e54e62ef773264488.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/27803122/large_760beb3c56de8ec3f8d92333f022b5de.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/27803119/large_4daef8673c30a85b7f09ee1f6eb9acfc.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/27022761/large_82302a19ca7e63ff8e34cb06241f8a32.jpg'), 9),
                                                            (10, ('https://cdn-assets.alltrails.com/uploads/photo/image/34355203/large_2f86528998182ea4726f0a37812bd687.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/29169048/large_8a75f9d1bc5f9b6c7e7c6e2e5469f9ac.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/24966362/large_e08b8c8ce34a737c871fa0c7e6d835db.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/17439007/large_e4b3b9d0bb260d6d94536b18edc2a640.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/19743288/large_54b445b87dfcf61012a481882820015e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/29169079/large_4581abfd8fe8087a0ad9c3f8014260da.jpg'), 10),
                                                            (11, ('https://cdn-assets.alltrails.com/uploads/photo/image/37093470/large_f4107a52b9407a87068e7819cd667957.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37067481/large_3b5cec361b268568156304fb5fd259a8.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37093472/large_58cc0c6d83bfd078108fce759aea7632.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36838777/large_e13cd838207e13e34629153342e3749d.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36815482/large_22d743c5eedd5fc2eba767e40371aabd.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34143223/large_45c2f966a565b499d7971ddb2b980697.jpg'), 11),
                                                            (12, ('https://cdn-assets.alltrails.com/uploads/photo/image/36595874/large_3c1e711e96c5597d78daf21897921358.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36578730/large_f161770be04a35c6093ca675ee935a30.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36595916/large_d7942ddd22f2df7bcce3b16225b3b912.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36331230/large_21ce02c74194e31ea4d051c4cc347c77.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36331228/large_f0dfce08b199f7ef2a52da081cd2d2eb.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35248998/large_838cc0379c84ce0286c71472c4d8f465.jpg'), 12),
                                                            (13, ('https://cdn-assets.alltrails.com/uploads/photo/image/37387495/large_ffb19eec0b7f64433f3aa658c9d5f7cc.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37050687/large_f37808ba8ce136d4f86b066042726822.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37050682/large_c21b537adbd60eb9e47e73dd697c4936.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37028940/large_f4bd5185bfd51b20ebba9f1b066d6b36.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37028958/large_8b7dd6a03ab390cf78c2416ca93fbc5f.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36910375/large_730c71bd8a12ccb9b3b46e76851480ce.jpg'), 13),
                                                            (14, ('https://cdn-assets.alltrails.com/uploads/photo/image/37050682/large_c21b537adbd60eb9e47e73dd697c4936.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36997284/large_0aff662a001d2d4faf1a18646c0b60fc.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35639843/large_27912d5547984e47fb8a6890ff5c6fca.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34993044/large_2cb1429c6ad5c9ff6b16fff8b9a155f7.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34571268/large_e859a807a4cbd1b542e42f1935fcfd19.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34049539/large_2bb95bc2523954b14ed0c0a6e8d5b025.jpg'), 14),
                                                            (15, ('https://cdn-assets.alltrails.com/uploads/photo/image/37326411/large_91803bd20ec5e4a94137e6b17f09934f.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37326419/large_83f8e8b0b381b1a73a7dce4f043c2f70.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37152051/large_07a2901d11d35f1495bdab759bf3ba02.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36091686/large_d3d8ba99b1ebd13f3519c79defbfea45.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35118417/large_fdb18627551cc42594182e3598e9a839.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/34248351/large_9f7b8aff0d8f41cbe1f64ecb39bf58a5.jpg'), 15),
                                                            (16, ('https://cdn-assets.alltrails.com/uploads/photo/image/37455071/large_995a48fa7f8b607cc9c2fa63d8519997.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/33246872/large_22759e015ceafdb868f3c6bcb0ac23db.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37455066/large_0ac67e14ed3bc2563802e3462b03fd42.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37455063/large_129f3c62e3cce5fc88a76d981d06a53d.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37455064/large_7b615e0f72b56fac8cccb1bf0ce3523f.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/32192383/large_e7f8d28042373405855a99c9d21dc248.jpg'), 16),
                                                            (17, ('https://cdn-assets.alltrails.com/uploads/photo/image/38151969/large_28052a0abd798e284e457ecdc950e7f9.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/38168819/large_417e3d083c8be5eff0f5113d2964b4ae.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/38151963/large_e830cd80c92fcec6d3fb6bec25f9813e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/38022715/large_922dbfffe31108714007e622cb45fb86.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/38016009/large_db33e492e36d6ade8707ca7e1e5f0990.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/38168816/large_8c42238934a3d8824446451e782891ee.jpg'), 17),
                                                            (18, ('https://cdn-assets.alltrails.com/uploads/photo/image/10129951/large_5aeccd56cc54808464ebd234718664fa.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/10097866/extra_large_97806e6fe121cc6716bbcfabe5b04845.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/11490250/extra_large_546285169fed8afb07ae77847209c8f8.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/19747372/extra_large_9e357c36f0eaa0538b66b5de2614da62.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/11221856/extra_large_e67e8641ca47d608c8316e7864999e63.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/24707431/extra_large_a5fc8f552765806a1f2098d94ec9cc71.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/20136200/extra_large_56812b61460da1598d2794134d80dfe6.jpg'), 18),
                                                            (19, ('https://cdn-assets.alltrails.com/uploads/photo/image/11370583/extra_large_b9a5a209b12607bfca265ceaa5e8d699.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37886167/extra_large_5a9d905537fa46fe31508a4d289eb929.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37015567/extra_large_79eff341b52f074efb0875286d91ad3e.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37886166/extra_large_ba36f229b80a3e0fb62490f24a3f07eb.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36197351/extra_large_3cebdd702e33d6c6be17ce5cbd1724d4.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37015573/extra_large_2cce63c13f21f31611169b03db91bf0a.jpg'), 19),
                                                            (20, ('https://cdn-assets.alltrails.com/uploads/photo/image/32928734/extra_large_a22fcea07716a6128d56dc6121c26def.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37931058/large_9cbe929f0ae10a2e32b2f1ced1fb24fe.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37250075/large_1845f809f16cc661535778a4a7be6ea5.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36049741/large_87323dde6074522e63d982976d2900ea.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35680460/large_a82e10520721b4e33a29d4003b93b1a2.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35680465/large_b44c22833fc056a2a38dcbaaabef64bf.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/35680423/large_dcd484ea69f6f9de794f92fa9b6b4166.jpg'), 20),
                                                            (21, ('https://cdn-assets.alltrails.com/uploads/photo/image/37719089/large_c73f8901e019d2c66aeea7e77f1de126.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37747691/large_93182d3f6bb022d14a43e540480a19e5.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37717458/large_8d6dc9f8ce7629b3dd0e07db90a4fdee.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37717457/large_d8751b433d977474a38343a1b5d18ab2.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/37257668/large_a50a444023dc21c8bcfccdbd91502419.jpg', 'https://cdn-assets.alltrails.com/uploads/photo/image/36100080/large_2576257907b1c8d1d37c94783306654f.jpg'), 21)




# INSERT INTO events (id, name, date, time, meet_location, meet_time, event_details, owner_id, trail_id)
# VALUES
# (1, 'Cool Trail', '2021-06-21', '10:00', 'Burger King', '0700', 'yes', 2, 6)
# (2, 'Hills', '2021-09-20', '13:00', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);
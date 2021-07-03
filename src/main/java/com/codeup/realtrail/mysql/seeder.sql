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

INSERT INTO trails(id, name, difficulty_level, rating, length, elevation, type, trail_details)
VALUES
       (1, 'Joe Johnston Route',
       'Moderate',
       4.4,
       9.3,
       652,
       'Loop',
       'Government Canyon State Natural Area charges an entrance fee per adult, while children 12 and under are free. Reservations can be made at https://tpwd.texas.gov/state-parks/government-canyon. Trails may close due to poor conditions or weather. Check out their Facebook accounts (https://www.facebook.com/TPWDGCSNA/) for the latest trail reports, or call the park. Nice Point to Point trail. Dinosaur footprints recently found has added to the traffic. Rocky, mild elevations pretty canyon where the footprints were discovered.'),
       (2, 'Savannah Loop',
        'Easy',
        4.4,
        2.3,
        62,
        'Loop',
        'Savannah Trail Loop is a 2.3 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips and is accessible year-round. Dogs are also able to use this trail but must be kept on leash.'),
       (3, 'Lytle''s Loop',
        'Easy',
        4.2,
        4.4,
        131,
        'Loop',
        'Lytle''s Loop Trail is a 4.4 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail offers a number of activity options and is best used from February until October. Dogs are also able to use this trail but must be kept on leash.'),
       (4, 'Wildcat Canyon Trail',
        'Easy',
        4.4,
        5.4,
        301,
        'Out and back',
        'Government Canyon Wildcat Canyon Trail is a 5.4 mile moderately trafficked out and back trail located near San Antonio, Texas that features beautiful wild flowers and is rated as moderate. The trail offers a number of activity options and is accessible year-round.'),
       (5, 'Sendero Balcones',
        'Easy',
        2.8,
        4.5,
        331,
        'Point to point',
        'Stretching across Government Canyon''s backcountry, Sendero Balcones cuts a path along the top of the Balcones Escarpment. This rough, rock-strewn trail requires constant attention to pathfinding and can be very mentally tiring.

The track splits off from Recharge Trail at a signed map and a trail marker just east of Bluff Spurs. It is a rocky, moderately technical singletrack, at times widening to doubletrack. The rocks come in varying sizes. While they don''t require any climbing, they often create hazardous footing throughout Sendero Balcones'' four-plus miles, and therein lies most of the challenge of this route. It requires an eyes-down approach to hiking and a slower pace than other area trails. On the up side, nearly the entire trail is covered by tree canopy, providing plenty of shade from the scorching Texas sun.

The path makes a steady, low-grade climb up to the top of one of the Balcones'' many hills over its first mile. The second entry to Bluff Spurs is found about a half mile in, at another map/marker intersection. From here, it''s a remote-feeling mile and a half to the next intersection, with a few brief smooth segments breaking up the constant rock hopping and dodging. Thanks to the thick tree cover, the view doesn''t change much, but the plant life makes the air fragrant and cool.'),
       (6, 'Black Hill Loop',
        'Easy',
        4.3,
        5.0,
        283,
        'Point to point',
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
        'Nice little trail. Narrow in some areas. Clearly marked though.

        Easy walk thru Crownridge Canyon Natural Area. Several plant reclamation areas throughout the park with small informative plaques.'),
       (9, 'Red Oak Trail',
        'Easy',
        4.2,
        1.3,
        72,
        'Loop',
        'Nice little trail. Narrow in some areas. Clearly marked though.

        You''ll see the marker for Bear Grass at the top of the Red Oak trail. It goes off the pavement into natural terrain. Steep in areas and muddy after rain.'),
       (10, 'Apache Creek Greenway Trail',
        'Moderate',
        3.5,
        12.3,
        259,
        'Cycleway',
        'Apache Creek Greenway is a 12.3 mile out and back trail located near San Antonio, Texas. The trail is rated as moderate and offers a number of activity options.'),
       (11, 'Medina River Greenway Trail',
        'Easy',
        4.4,
        13.3,
        413,
        'Cycleway',
        'This is a nice long greenway trail that starts at the golf course and has a varied terrain - lakes, fields, and the river.

Accessibility: There are 2 designated accessible spaces in the paved parking lot off of Clubhouse Boulevard at the east end of the trail. Both of them are van-accessible with a striped access aisle. The trail surface is cement, smooth, and typically at least 6 feet wide. The most accessible portion of the trail is the first 3.5 miles out. The estimated grade is mostly gentle (5% or less) except for very steep (over 12%) uphill and downhill sections at about 4.0 through 12.0 miles when going west.  Wheelchair/mobility equipment or stroller users may need assistance in the steeper sections or to avoid them for safety. There are benches along the route for resting.'),
       (12, 'Salado Creek Greenway Trail',
        'Easy',
        4.6,
        22.7,
        354,
        'Cycleway',
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
        'Restoration Way Trail Loop is a 5.1 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips. SEASONAL CLOSURE: This area is subject to seasonal closure due to weather conditions. For more information, please visit https://www.sanantonio.gov/ParksAndRec/Parks-Facilities/All-Parks-Facilities/Parks-Facilities-Details/ArtMID/14820/ArticleID/2649/Friedrich-Wilderness-Park'),
       (15, 'Blue Loop',
        'Moderate',
        4.3,
        6.7,
        236,
        'Loop',
        'Great park for mountain biking.  The blue loop is fairly easy; however, it can get tough during the summer months when temperatures soar and there is little shade.  Plan to start early to beat the heat and the crowd.

        There are bathrooms by the parking lot and throughout the park.

        Accessibility: There is a combination of paved and dirt areas on this trail, and the dirt sections may not be accessible for mobility equipment or strollers. The estimated grade is moderately steep, up to 8%. The paved sections are typically at least three feet wide, but the dirt sections may be as narrow as 1.5 feet wide, may be rocky, and may have a cross-slope of more than 2%.'),
       (16, 'Baseball Field Trail',
        'Easy',
        4.4,
        2.4,
        32,
        'Loop',
        'This is a short but enjoyable gentle and paved multi-use path through wooded areas. For a longer route, you can continue on the blue trail but it is reportedly rockier.

        Accessibility: There are 3 designated accessible spaces in the paved parking lot off of Turke Roost at the north end of the trail. All of them are van-accessible with striped access aisles. The trail surface is paved asphalt with bumps and cracks. It is typically at least 5 feet wide. The majority of the trail is estimated to be in the gentle (3% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users. '),
       (17, 'Red Trail',
        'Easy',
        3.5,
        1.9,
        47,
        'Point to point',
        'McAllister Park Red trail is connected to Blue trail and you can go to some other trails form here. Just be aware of mountain bikers.'),
       (18, 'Olmos Basin Loop Trail',
        'Easy',
        4.0,
        2.4,
        196,
        'Loop',
        'Olmos Basin Trail is good for all skill levels. The trail is primarily used for running and nature trips. Dogs are also able to use this trail but must be kept on leash.'),
       (19, 'Acequia Trail',
        'Easy',
        4.6,
        15.1,
        413,
        'Cycleway',
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
        'History, River, Plaza, Cathedral, Tower, Alamo, parks, Rivercenter, King William

        This is a 15 mile long series of walkways that run along the San Antonio river, with shopping, dining and other attractions along the route. Walk on sidewalks at river-level & on west side of the River. The Walk ends at a wide stairs going up to old town square, Bexar County Courthouse, & San Fernando Cathedral. South on Soledad St. to Nueva St. & re-enter River Walk at the dam. Go to end & cross Guenther St. bridge. Pass thru King William homes & re-enter River Walk on east bank. Go to La Villita River Theatre. Through La Villita, Hemisfair Plaza, to Inst. of Texan Cultures & Tower. North to Commerce St., north on Alamo St. to The Alamo. Cross Alamo St. into River Center. Re-enter River Walk & north to footbridge. Right & back to bridge at dam. Right & north along east bank of river. Leave River Walk at Travis St. & follow to Travis Park. Diagonally across Park & follow Jefferson St. by war memorials & auditorium to Lexington. Left back to Sheraton'),
       (21, 'Leon Creek Greenway Trail',
        'Easy',
        4.4,
        12.3,
        196,
        'Point to point',
        'Leon Creek Greenway Trail is a 12.3 mile heavily trafficked point-to-point trail located near Shavano Park, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for walking, running, and road biking and is accessible year-round. Dogs are also able to use this trail but must be kept on leash.

        The route shown on this page is the flat paved path along the creek. There are numerous dirt mountain biking and hiking trails off of this paved trail. Electric motor vehicles are reportedly not allowed on this trail, and you may get ticketed if you use them.

Accessibility: There are 2 designated accessible spaces in the paved parking lot off of North Loop 1604 West at the north end of the trail. Both of them are van-accessible with striped access aisles. There are 4 designated accessible spaces in the paved parking lot off of North Western at the south end of the trail. All of them are van-accessible with striped access aisles.

The trail surface is paved concrete and smooth. It is typically at least 8 feet wide. The majority of the trail is estimated to be in the mostly gentle (5% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users. ');

INSERT INTO events (id, name, date, time, location, meet_location, meet_time, event_details, owner_id, trail_id)
VALUES
(1, 'Cool Trail', '2021-06-21', '10:00', 'San Antonio', 'Burger King', '0700', 'yes', 2, 6),
(2, 'Hills', '2021-09-20', '13:00', 'San Antonio', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);
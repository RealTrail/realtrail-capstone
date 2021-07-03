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
        (2, 'Bear Grass Trail',
        'Moderate',
        4.0,
        1.8,
        150,
        'Loop',
        'Nice little trail. Narrow in some areas. Clearly marked though.

        Easy walk thru Crownridge Canyon Natural Area. Several plant reclamation areas throughout the park with small informative plaques.'),
       (3, 'Red Oak Trail',
        'Easy',
        4.2,
        1.3,
        72,
        'Loop',
        'Nice little trail. Narrow in some areas. Clearly marked though.

        You''ll see the marker for Bear Grass at the top of the Red Oak trail. It goes off the pavement into natural terrain. Steep in areas and muddy after rain.'),
       (4, 'Apache Creek Greenway Trail',
        'Moderate',
        3.5,
        12.3,
        259,
        'Cycleway',
        'Apache Creek Greenway is a 12.3 mile out and back trail located near San Antonio, Texas. The trail is rated as moderate and offers a number of activity options.'),
       (5, 'Medina River Greenway Trail',
        'Easy',
        4.4,
        13.3,
        413,
        'Cycleway',
        'This is a nice long greenway trail that starts at the golf course and has a varied terrain - lakes, fields, and the river.

Accessibility: There are 2 designated accessible spaces in the paved parking lot off of Clubhouse Boulevard at the east end of the trail. Both of them are van-accessible with a striped access aisle. The trail surface is cement, smooth, and typically at least 6 feet wide. The most accessible portion of the trail is the first 3.5 miles out. The estimated grade is mostly gentle (5% or less) except for very steep (over 12%) uphill and downhill sections at about 4.0 through 12.0 miles when going west.  Wheelchair/mobility equipment or stroller users may need assistance in the steeper sections or to avoid them for safety. There are benches along the route for resting.'),
       (6, 'Salado Creek Greenway Trail',
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
       (7, 'Main Loop',
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
       (8, 'Restoration Way Trail',
        'Easy',
        4.5,
        5.1,
        511,
        'Loop',
        'Restoration Way Trail Loop is a 5.1 mile heavily trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips. SEASONAL CLOSURE: This area is subject to seasonal closure due to weather conditions. For more information, please visit https://www.sanantonio.gov/ParksAndRec/Parks-Facilities/All-Parks-Facilities/Parks-Facilities-Details/ArtMID/14820/ArticleID/2649/Friedrich-Wilderness-Park'),
       (9, 'Blue Loop',
        'Moderate',
        4.3,
        6.7,
        236,
        'Loop',
        'Great park for mountain biking.  The blue loop is fairly easy; however, it can get tough during the summer months when temperatures soar and there is little shade.  Plan to start early to beat the heat and the crowd.

        There are bathrooms by the parking lot and throughout the park.

        Accessibility: There is a combination of paved and dirt areas on this trail, and the dirt sections may not be accessible for mobility equipment or strollers. The estimated grade is moderately steep, up to 8%. The paved sections are typically at least three feet wide, but the dirt sections may be as narrow as 1.5 feet wide, may be rocky, and may have a cross-slope of more than 2%.'),
       (10, 'Baseball Field Trail',
        'Easy',
        4.4,
        2.4,
        32,
        'Loop',
        'This is a short but enjoyable gentle and paved multi-use path through wooded areas. For a longer route, you can continue on the blue trail but it is reportedly rockier.

        Accessibility: There are 3 designated accessible spaces in the paved parking lot off of Turke Roost at the north end of the trail. All of them are van-accessible with striped access aisles. The trail surface is paved asphalt with bumps and cracks. It is typically at least 5 feet wide. The majority of the trail is estimated to be in the gentle (3% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users. '),
       (11, 'Red Trail',
        'Easy',
        3.5,
        1.9,
        47,
        'Point to point',
        'McAllister Park Red trail is connected to Blue trail and you can go to some other trails form here. Just be aware of mountain bikers.'),
       (12, 'Olmos Basin Trail',
        'Easy',
        4.0,
        2.4,
        196,
        'Loop',
        'Olmos Basin Trail is good for all skill levels. The trail is primarily used for running and nature trips. Dogs are also able to use this trail but must be kept on leash.'),
       (13, 'Acequia Trail',
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
#        (12, 'Walker Ranch Loop Trail ',
#         'Easy',
#         4.3,
#         0.6,
#         13,
#         'Loop',
#         'Walker Ranch Loop Trail is a 0.6 mile moderately trafficked loop trail located near San Antonio, Texas. The trail is good for all skill levels and primarily used for hiking and walking. Dogs are also able to use this trail but must be kept on leash.'),
#        (13, 'Bluff Trail and Old Tower Climb Trail',
#         'Easy',
#         4.5,
#         1.2,
#         65,
#         'Loop',
#         'This is a paved loop with many attached dirt trails if you want to explore the area.
#
#         Accessibility: This concrete trail is estimated to typically be at least 6 feet wide. The slope may be steep in one location and estimated to range overall from 0 to 9% grade. The steepest section, estimated up to 9% grade, is at the 0.9 mile mark. Trail goers with mobility equipment may need assistance to navigate this section safely. There are at least 2 ADA-compliant car parking spots with access aisles at the trailhead.'),
#        (14, 'McGimsey Scout Trail',
#         'Easy',
#         3.2,
#         3.3,
#         150,
#         'Loop',
#         'McGimsey Scout Trail is a 3.3 mile moderately trafficked loop trail located near San Antonio, Texas that features beautiful wild flowers and is good for all skill levels. The trail is primarily used for hiking, running, nature trips, and bird watching and is accessible year-round. Dogs are also able to use this trail but must be kept on leash.'),
#        (15, 'Wilderness and Wildlife Trails',
#         'Easy',
#         4.1,
#         1.0,
#         16,
#         'Loop',
#         'The main trail at Brackenridge Park is paved and there are dozens of different combinations so you can change up your route every time. There are some streams along the way.
#
#         Accessibility: The trail surface is paved and typically at least five feet wide. The estimated grade is gentle (all 3% or less). The paved surface has some cracks so the ride may be bumpy for mobility equipment or stroller users.'),
#        (16, 'San Antonio River Walk Mission Reach Trail',
#         'Easy',
#         4.4,
#         5.7,
#         141,
#         'Loop',
#     'History, River, Plaza, Cathedral, Tower, Alamo, parks, Rivercenter, King William
#
#         Walk on sidewalks at river-level & on west side of the River. The Walk ends at a wide stairs going up to old town square, Bexar County Courthouse, & San Fernando Cathedral. South on Soledad St. to Nueva St. & re-enter River Walk at the dam. Go to end & cross Guenther St. bridge. Pass thru King William homes & re-enter River Walk on east bank. Go to La Villita River Theatre. Through La Villita, Hemisfair Plaza, to Inst. of Texan Cultures & Tower. North to Commerce St., north on Alamo St. to The Alamo. Cross Alamo St. into River Center. Re-enter River Walk & north to footbridge. Right & back to bridge at dam. Right & north along east bank of river. Leave River Walk at Travis St. & follow to Travis Park. Diagonally across Park & follow Jefferson St. by war memorials & auditorium to Lexington. Left back to Sheraton'),
#        (17, 'Leon Creek  Greenway: Ingram to Military Dr.',
#         'Easy',
#         4.3,
#         4.0,
#         78,
#         'Out and back',
#         'This is a clean and gentle paved trail, wide enough for bikers to pass each other, with nice forest scenery.’
#
#         Accessibility: There are 4 designated accessible spaces in the paved parking lot off of North Western at the north end of the trail. All of them are van-accessible with striped access aisles. There is 1 designated accessible space in the paved parking lot off of West Military Drive at the south end of the trail. It is van-accessible with striped access aisles.
#
#         The trail surface is paved concrete and smooth. It is typically at least 6 feet wide. The majority of the trail is estimated to be in the gentle (3% or less) grade category. This trail will likely be navigable for most wheelchairs/mobility equipment or stroller users.'),
#        (18, 'Leon Creek Greenway: Ingram to Cathedral Rock',
#         'Moderate',
#         4.5,
#         4.9,
#         190,
#         'Out and back',
#         'This is a concrete paved, well maintained out and back trail along the Leon Creek in San Antonio. There is ample free parking at both the Ingram Transit, and the Cathedral Rock trailheads.'),
#        (19, 'Tom Slick Community',
#         'Easy',
#         4.1,
#         1.2,
#         19,
#         'Loop',
#         'It''s a good little park, one run around is half a mile, theres a beautiful pond right in the middle, its pretty and relaxing. There''s a little dog playground which is really cute, great for taking your dogs too to relieve them from some stay at home stress :), there''s also a kids playground.'''),
#        (20, 'Woodlawn Lake Loop',
#            'Easy',
#         4.4,
#         1.4,
#         16,
#         'Loop',
#         'Woodlawn Lake Loop is a 1.4 mile moderately trafficked loop trail located near San Antonio, Texas that features a lake and is good for all skill levels. The trail is primarily used for hiking, walking, running, and nature trips and is accessible year-round. Dogs are also able to use this trail.');

# INSERT INTO events (id, name, date, time, location, meet_location, meet_time, event_details, owner_id, trail_id)
# VALUES
# (1, 'Cool Trail', '2021-06-21', '10:00', 'San Antonio', 'Burger King', '0700', 'yes', 2, 6),
# (2, 'Hills', '2021-09-20', '13:00', 'San Antonio', 'Mcdonald', '12:00', 'The best ride of your life', 4, 7);
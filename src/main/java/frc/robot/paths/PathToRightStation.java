package frc.robot.paths;

public class PathToRightStation {
    // Position, velocity, acceleration, duration
    public static double[][] leftPoints = new double[][]{
		{ 0.000652, 0.065190, 3.259519, 0.020000 },
		{ 0.000744, 0.004619, -3.028550, 0.020000 },
		{ 0.000929, 0.009244, 0.231253, 0.020000 },
		{ 0.001207, 0.013881, 0.231822, 0.020000 },
		{ 0.010458, 0.462554, 22.433651, 0.020000 },
		{ 0.023960, 0.675107, 10.627668, 0.020000 },
		{ 0.040164, 0.810205, 6.754885, 0.020000 },
		{ 0.059072, 0.945370, 6.758249, 0.020000 },
		{ 0.080683, 1.080578, 6.760405, 0.020000 },
		{ 0.104999, 1.215805, 6.761342, 0.020000 },
		{ 0.132021, 1.351067, 6.763118, 0.020000 },
		{ 0.161751, 1.486516, 6.772423, 0.020000 },
		{ 0.194190, 1.621948, 6.771628, 0.020000 },
		{ 0.229339, 1.757477, 6.776443, 0.020000 },
		{ 0.267202, 1.893120, 6.782137, 0.020000 },
		{ 0.307779, 2.028852, 6.786621, 0.020000 },
		{ 0.351072, 2.164650, 6.789882, 0.020000 },
		{ 0.397087, 2.300740, 6.804492, 0.020000 },
		{ 0.445822, 2.436753, 6.800675, 0.020000 },
		{ 0.497281, 2.572989, 6.811792, 0.020000 },
		{ 0.551470, 2.709421, 6.821605, 0.020000 },
		{ 0.608389, 2.845972, 6.827563, 0.020000 },
		{ 0.668044, 2.982732, 6.837958, 0.020000 },
		{ 0.730436, 3.119587, 6.842788, 0.020000 },
		{ 0.795570, 3.256721, 6.856701, 0.020000 },
		{ 0.863450, 3.393987, 6.863264, 0.020000 },
		{ 0.934081, 3.531536, 6.877471, 0.020000 },
		{ 1.007467, 3.669347, 6.890541, 0.020000 },
		{ 1.083615, 3.807396, 6.902435, 0.020000 },
		{ 1.162529, 3.945658, 6.913117, 0.020000 },
		{ 1.244213, 4.084220, 6.928080, 0.020000 },
		{ 1.328675, 4.223090, 6.943510, 0.020000 },
		{ 1.415922, 4.362341, 6.962588, 0.020000 },
		{ 1.505959, 4.501856, 6.975708, 0.020000 },
		{ 1.598794, 4.641745, 6.994457, 0.020000 },
		{ 1.694436, 4.782113, 7.018400, 0.020000 },
		{ 1.792890, 4.922702, 7.029472, 0.020000 },
		{ 1.894169, 5.063942, 7.062001, 0.020000 },
		{ 1.998279, 5.205495, 7.077626, 0.020000 },
		{ 2.105230, 5.347564, 7.103445, 0.020000 },
		{ 2.215032, 5.490130, 7.128292, 0.020000 },
		{ 2.327700, 5.633366, 7.161824, 0.020000 },
		{ 2.443240, 5.777002, 7.181799, 0.020000 },
		{ 2.561669, 5.921458, 7.222779, 0.020000 },
		{ 2.682998, 6.066436, 7.248934, 0.020000 },
		{ 2.807240, 6.212117, 7.284022, 0.020000 },
		{ 2.934413, 6.358665, 7.327413, 0.020000 },
		{ 3.064530, 6.505827, 7.358105, 0.020000 },
		{ 3.197610, 6.653990, 7.408127, 0.020000 },
		{ 3.333668, 6.802932, 7.447141, 0.020000 },
		{ 3.472725, 6.952827, 7.494709, 0.020000 },
		{ 3.614800, 7.103771, 7.547242, 0.020000 },
		{ 3.759915, 7.255741, 7.598490, 0.020000 },
		{ 3.908092, 7.408857, 7.655810, 0.020000 },
		{ 4.059355, 7.563114, 7.712810, 0.020000 },
		{ 4.213727, 7.718611, 7.774889, 0.020000 },
		{ 4.371237, 7.875515, 7.845169, 0.020000 },
		{ 4.531910, 8.033666, 7.907543, 0.020000 },
		{ 4.695779, 8.193407, 7.987083, 0.020000 },
		{ 4.862871, 8.354616, 8.060450, 0.020000 },
		{ 5.033220, 8.517436, 8.140998, 0.020000 },
		{ 5.206856, 8.681804, 8.218376, 0.020000 },
		{ 5.383815, 8.847956, 8.307619, 0.020000 },
		{ 5.564133, 9.015900, 8.397216, 0.020000 },
		{ 5.747844, 9.185584, 8.484160, 0.020000 },
		{ 5.934985, 9.357007, 8.571190, 0.020000 },
		{ 6.125591, 9.530303, 8.664765, 0.020000 },
		{ 6.319699, 9.705412, 8.755452, 0.020000 },
		{ 6.517343, 9.882194, 8.839096, 0.020000 },
		{ 6.718554, 10.060573, 8.918946, 0.020000 },
		{ 6.923364, 10.240481, 8.995392, 0.020000 },
		{ 7.131798, 10.421708, 9.061384, 0.020000 },
		{ 7.343877, 10.603942, 9.111703, 0.020000 },
		{ 7.559616, 10.786955, 9.150632, 0.020000 },
		{ 7.779022, 10.970297, 9.167104, 0.020000 },
		{ 8.002093, 11.153550, 9.162648, 0.020000 },
		{ 8.228816, 11.336151, 9.130069, 0.020000 },
		{ 8.458418, 11.480120, 7.198458, 0.020000 },
		{ 8.688742, 11.516180, 1.802961, 0.020000 },
		{ 8.917599, 11.442871, -3.665421, 0.020000 },
		{ 9.144009, 11.320494, -6.118842, 0.020000 },
		{ 9.367737, 11.186390, -6.705203, 0.020000 },
		{ 9.588688, 11.047542, -6.942403, 0.020000 },
		{ 9.806770, 10.904107, -7.171785, 0.020000 },
		{ 10.021895, 10.756243, -7.393161, 0.020000 },
		{ 10.233982, 10.604375, -7.593436, 0.020000 },
		{ 10.442957, 10.448745, -7.781463, 0.020000 },
		{ 10.648753, 10.289788, -7.947867, 0.020000 },
		{ 10.851313, 10.128019, -8.088467, 0.020000 },
		{ 11.050586, 9.963639, -8.219002, 0.020000 },
		{ 11.246534, 9.797378, -8.313019, 0.020000 },
		{ 11.439123, 9.629473, -8.395246, 0.020000 },
		{ 11.628331, 9.460394, -8.453951, 0.020000 },
		{ 11.814141, 9.290512, -8.494098, 0.020000 },
		{ 11.996546, 9.120231, -8.514078, 0.020000 },
		{ 12.175540, 8.949711, -8.525968, 0.020000 },
		{ 12.351129, 8.779451, -8.513012, 0.020000 },
		{ 12.523317, 8.609392, -8.502977, 0.020000 },
		{ 12.692115, 8.439921, -8.473541, 0.020000 },
		{ 12.857540, 8.271233, -8.434387, 0.020000 },
		{ 13.019608, 8.103376, -8.392835, 0.020000 },
		{ 13.178337, 7.936490, -8.344342, 0.020000 },
		{ 13.333751, 7.770655, -8.291727, 0.020000 },
		{ 13.485867, 7.605814, -8.242071, 0.020000 },
		{ 13.634712, 7.442250, -8.178188, 0.020000 },
		{ 13.780305, 7.279653, -8.129828, 0.020000 },
		{ 13.922669, 7.118206, -8.072341, 0.020000 },
		{ 14.061831, 6.958092, -8.005738, 0.020000 },
		{ 14.197810, 6.798940, -7.957563, 0.020000 },
		{ 14.330630, 6.641018, -7.896111, 0.020000 },
		{ 14.460312, 6.484112, -7.845314, 0.020000 },
		{ 14.586879, 6.328357, -7.787730, 0.020000 },
		{ 14.710348, 6.173409, -7.747437, 0.020000 },
		{ 14.830742, 6.019718, -7.684516, 0.020000 },
		{ 14.948078, 5.866779, -7.646963, 0.020000 },
		{ 15.062372, 5.714699, -7.604014, 0.020000 },
		{ 15.173643, 5.563586, -7.555644, 0.020000 },
		{ 15.281913, 5.413487, -7.504919, 0.020000 },
		{ 15.387188, 5.263740, -7.487359, 0.020000 },
		{ 15.489489, 5.115050, -7.434506, 0.020000 },
		{ 15.588830, 4.967042, -7.400412, 0.020000 },
		{ 15.685225, 4.819746, -7.364785, 0.020000 },
		{ 15.778681, 4.672844, -7.345132, 0.020000 },
		{ 15.869218, 4.526847, -7.299806, 0.020000 },
		{ 15.956841, 4.381150, -7.284896, 0.020000 },
		{ 16.041566, 4.236214, -7.246790, 0.020000 },
		{ 16.123400, 4.091711, -7.225152, 0.020000 },
		{ 16.202354, 3.947722, -7.199453, 0.020000 },
		{ 16.278439, 3.804226, -7.174788, 0.020000 },
		{ 16.351656, 3.660846, -7.168990, 0.020000 },
		{ 16.422022, 3.518293, -7.127667, 0.020000 },
		{ 16.489541, 3.375994, -7.114946, 0.020000 },
		{ 16.554220, 3.233908, -7.104266, 0.020000 },
		{ 16.616063, 3.092167, -7.087082, 0.020000 },
		{ 16.675078, 2.950759, -7.070396, 0.020000 },
		{ 16.731274, 2.809814, -7.047263, 0.020000 },
		{ 16.784655, 2.669052, -7.038105, 0.020000 },
		{ 16.835226, 2.528538, -7.025657, 0.020000 },
		{ 16.882988, 2.388114, -7.021210, 0.020000 },
		{ 16.927951, 2.248118, -6.999828, 0.020000 },
		{ 16.970116, 2.108280, -6.991892, 0.020000 },
		{ 17.009490, 1.968669, -6.980533, 0.020000 },
		{ 17.046072, 1.829094, -6.978782, 0.020000 },
		{ 17.079865, 1.689681, -6.970648, 0.020000 },
		{ 17.110878, 1.550625, -6.952801, 0.020000 },
		{ 17.139109, 1.411576, -6.952404, 0.020000 },
		{ 17.164559, 1.272484, -6.954643, 0.020000 },
		{ 17.187234, 1.133725, -6.937936, 0.020000 },
		{ 17.207134, 0.995042, -6.934131, 0.020000 },
		{ 17.224263, 0.856422, -6.931020, 0.020000 },
		{ 17.238615, 0.717606, -6.940809, 0.020000 },
		{ 17.250202, 0.579339, -6.913358, 0.020000 },
		{ 17.259015, 0.440644, -6.934744, 0.020000 },
		{ 17.265062, 0.302383, -6.913011, 0.020000 },
		{ 17.268342, 0.163983, -6.920019, 0.020000 },
		{ 17.269417, 0.053754, -5.511440, 0.020000 },
		{ 17.269545, 0.006377, -2.368865, 0.020000 },
		{ 17.269545, 0.000000, -0.318849, 0.020000 },
    };
    
	public static double[][] rightPoints = new double[][]{
		{ 0.000652, 0.065190, 3.259519, 0.020000 },
		{ 0.000744, 0.004619, -3.028550, 0.020000 },
		{ 0.000929, 0.009244, 0.231253, 0.020000 },
		{ 0.001207, 0.013881, 0.231822, 0.020000 },
		{ 0.009716, 0.425477, 20.579799, 0.020000 },
		{ 0.022290, 0.628666, 10.159458, 0.020000 },
		{ 0.037376, 0.754326, 6.283002, 0.020000 },
		{ 0.054976, 0.879969, 6.282171, 0.020000 },
		{ 0.075087, 1.005560, 6.279527, 0.020000 },
		{ 0.097708, 1.131061, 6.275049, 0.020000 },
		{ 0.122837, 1.256474, 6.270640, 0.020000 },
		{ 0.150476, 1.381924, 6.272499, 0.020000 },
		{ 0.180620, 1.507208, 6.264218, 0.020000 },
		{ 0.213268, 1.632416, 6.260383, 0.020000 },
		{ 0.248419, 1.757547, 6.256571, 0.020000 },
		{ 0.286071, 1.882563, 6.250815, 0.020000 },
		{ 0.326219, 2.007425, 6.243081, 0.020000 },
		{ 0.368866, 2.132325, 6.244988, 0.020000 },
		{ 0.414004, 2.256903, 6.228904, 0.020000 },
		{ 0.461632, 2.381417, 6.225715, 0.020000 },
		{ 0.511749, 2.505824, 6.220322, 0.020000 },
		{ 0.564349, 2.630030, 6.210333, 0.020000 },
		{ 0.619431, 2.754098, 6.203367, 0.020000 },
		{ 0.676989, 2.877900, 6.190138, 0.020000 },
		{ 0.737021, 3.001583, 6.184108, 0.020000 },
		{ 0.799520, 3.124984, 6.170060, 0.020000 },
		{ 0.864485, 3.248219, 6.161752, 0.020000 },
		{ 0.931910, 3.371239, 6.151016, 0.020000 },
		{ 1.001789, 3.493994, 6.137751, 0.020000 },
		{ 1.074118, 3.616431, 6.121856, 0.020000 },
		{ 1.148890, 3.738597, 6.108282, 0.020000 },
		{ 1.226099, 3.860466, 6.093431, 0.020000 },
		{ 1.305741, 3.982068, 6.080112, 0.020000 },
		{ 1.387806, 4.103257, 6.059448, 0.020000 },
		{ 1.472288, 4.224095, 6.041911, 0.020000 },
		{ 1.559180, 4.344634, 6.026954, 0.020000 },
		{ 1.648472, 4.464595, 5.998042, 0.020000 },
		{ 1.740159, 4.584319, 5.986200, 0.020000 },
		{ 1.834228, 4.703449, 5.956482, 0.020000 },
		{ 1.930670, 4.822114, 5.933297, 0.020000 },
		{ 2.029475, 4.940242, 5.906365, 0.020000 },
		{ 2.130633, 5.057926, 5.884189, 0.020000 },
		{ 2.234130, 5.174858, 5.846608, 0.020000 },
		{ 2.339957, 5.291346, 5.824429, 0.020000 },
		{ 2.448098, 5.407053, 5.785334, 0.020000 },
		{ 2.558540, 5.522061, 5.750388, 0.020000 },
		{ 2.671268, 5.636435, 5.718719, 0.020000 },
		{ 2.786266, 5.749865, 5.671475, 0.020000 },
		{ 2.903518, 5.862600, 5.636784, 0.020000 },
		{ 3.023005, 5.974352, 5.587559, 0.020000 },
		{ 3.144708, 6.085168, 5.540834, 0.020000 },
		{ 3.268609, 6.195031, 5.493144, 0.020000 },
		{ 3.394685, 6.303807, 5.438788, 0.020000 },
		{ 3.522915, 6.411486, 5.383950, 0.020000 },
		{ 3.653273, 6.517943, 5.322869, 0.020000 },
		{ 3.785736, 6.623144, 5.260044, 0.020000 },
		{ 3.920278, 6.727103, 5.197960, 0.020000 },
		{ 4.056869, 6.829559, 5.122769, 0.020000 },
		{ 4.195483, 6.930676, 5.055850, 0.020000 },
		{ 4.336087, 7.030224, 4.977404, 0.020000 },
		{ 4.478652, 7.128204, 4.898995, 0.020000 },
		{ 4.623141, 7.224450, 4.812308, 0.020000 },
		{ 4.769522, 7.319059, 4.730451, 0.020000 },
		{ 4.917761, 7.411950, 4.644554, 0.020000 },
		{ 5.067821, 7.503016, 4.553286, 0.020000 },
		{ 5.219666, 7.592223, 4.460383, 0.020000 },
		{ 5.373259, 7.679680, 4.372861, 0.020000 },
		{ 5.528567, 7.765386, 4.285261, 0.020000 },
		{ 5.685553, 7.849328, 4.197098, 0.020000 },
		{ 5.844186, 7.931615, 4.114368, 0.020000 },
		{ 6.004434, 8.012441, 4.041278, 0.020000 },
		{ 6.166274, 8.091977, 3.976823, 0.020000 },
		{ 6.329682, 8.170421, 3.922200, 0.020000 },
		{ 6.494645, 8.248151, 3.886514, 0.020000 },
		{ 6.661156, 8.325501, 3.867485, 0.020000 },
		{ 6.829215, 8.402958, 3.872867, 0.020000 },
		{ 6.998835, 8.481035, 3.903808, 0.020000 },
		{ 7.169488, 8.532642, 2.580392, 0.020000 },
		{ 7.339659, 8.508531, -1.205593, 0.020000 },
		{ 7.507864, 8.410276, -4.912726, 0.020000 },
		{ 7.673542, 8.283907, -6.318446, 0.020000 },
		{ 7.836688, 8.157257, -6.332498, 0.020000 },
		{ 7.997395, 8.035365, -6.094612, 0.020000 },
		{ 8.155757, 7.918088, -5.863843, 0.020000 },
		{ 8.311860, 7.805149, -5.646971, 0.020000 },
		{ 8.465786, 7.696338, -5.440535, 0.020000 },
		{ 8.617611, 7.591220, -5.255906, 0.020000 },
		{ 8.767399, 7.489420, -5.089988, 0.020000 },
		{ 8.915211, 7.390577, -4.942148, 0.020000 },
		{ 9.061092, 7.294076, -4.825041, 0.020000 },
		{ 9.205086, 7.199689, -4.719359, 0.020000 },
		{ 9.347223, 7.106846, -4.642174, 0.020000 },
		{ 9.487527, 7.015184, -4.583078, 0.020000 },
		{ 9.626013, 6.924319, -4.543245, 0.020000 },
		{ 9.762692, 6.833948, -4.518537, 0.020000 },
		{ 9.897565, 6.743652, -4.514809, 0.020000 },
		{ 10.030632, 6.653329, -4.516147, 0.020000 },
		{ 10.161882, 6.562525, -4.540210, 0.020000 },
		{ 10.291306, 6.471186, -4.566939, 0.020000 },
		{ 10.418889, 6.379175, -4.600577, 0.020000 },
		{ 10.544615, 6.286295, -4.643983, 0.020000 },
		{ 10.668465, 6.192472, -4.691161, 0.020000 },
		{ 10.790417, 6.097631, -4.742028, 0.020000 },
		{ 10.910450, 6.001627, -4.800201, 0.020000 },
		{ 11.028542, 5.904619, -4.850419, 0.020000 },
		{ 11.144669, 5.806324, -4.914746, 0.020000 },
		{ 11.258806, 5.706874, -4.972486, 0.020000 },
		{ 11.370935, 5.606425, -5.022446, 0.020000 },
		{ 11.481029, 5.504706, -5.085966, 0.020000 },
		{ 11.589068, 5.401969, -5.136872, 0.020000 },
		{ 11.695030, 5.298089, -5.193966, 0.020000 },
		{ 11.798895, 5.193236, -5.242674, 0.020000 },
		{ 11.900638, 5.087187, -5.302432, 0.020000 },
		{ 12.000246, 4.980383, -5.340197, 0.020000 },
		{ 12.097696, 4.872476, -5.395372, 0.020000 },
		{ 12.192968, 4.763625, -5.442555, 0.020000 },
		{ 12.286048, 4.653993, -5.481580, 0.020000 },
		{ 12.376922, 4.543694, -5.514929, 0.020000 },
		{ 12.465567, 4.432243, -5.572549, 0.020000 },
		{ 12.551973, 4.320303, -5.597002, 0.020000 },
		{ 12.636125, 4.207628, -5.633752, 0.020000 },
		{ 12.718012, 4.094311, -5.665849, 0.020000 },
		{ 12.797614, 3.980146, -5.708291, 0.020000 },
		{ 12.874927, 3.865631, -5.725740, 0.020000 },
		{ 12.949933, 3.750309, -5.766095, 0.020000 },
		{ 13.022626, 3.634635, -5.783717, 0.020000 },
		{ 13.092994, 3.518382, -5.812629, 0.020000 },
		{ 13.161027, 3.401674, -5.835386, 0.020000 },
		{ 13.226718, 3.284545, -5.856457, 0.020000 },
		{ 13.290052, 3.166717, -5.891396, 0.020000 },
		{ 13.351029, 3.048852, -5.893270, 0.020000 },
		{ 13.409639, 2.930499, -5.917620, 0.020000 },
		{ 13.465873, 2.811667, -5.941639, 0.020000 },
		{ 13.519723, 2.692507, -5.957978, 0.020000 },
		{ 13.571184, 2.573051, -5.972788, 0.020000 },
		{ 13.620253, 2.453450, -5.980084, 0.020000 },
		{ 13.666923, 2.333494, -5.997774, 0.020000 },
		{ 13.711188, 2.213276, -6.010885, 0.020000 },
		{ 13.753042, 2.092689, -6.029365, 0.020000 },
		{ 13.792483, 1.972061, -6.031428, 0.020000 },
		{ 13.829507, 1.851186, -6.043748, 0.020000 },
		{ 13.864110, 1.730154, -6.051604, 0.020000 },
		{ 13.896287, 1.608824, -6.066502, 0.020000 },
		{ 13.926033, 1.487335, -6.074438, 0.020000 },
		{ 13.953351, 1.365885, -6.072500, 0.020000 },
		{ 13.978235, 1.244192, -6.084650, 0.020000 },
		{ 14.000679, 1.122234, -6.097902, 0.020000 },
		{ 14.020687, 1.000369, -6.093244, 0.020000 },
		{ 14.038255, 0.878393, -6.098785, 0.020000 },
		{ 14.053381, 0.756317, -6.103794, 0.020000 },
		{ 14.066060, 0.633936, -6.119080, 0.020000 },
		{ 14.076298, 0.511928, -6.100362, 0.020000 },
		{ 14.084087, 0.389454, -6.123704, 0.020000 },
		{ 14.089433, 0.267297, -6.107867, 0.020000 },
		{ 14.092333, 0.144970, -6.116367, 0.020000 },
		{ 14.093283, 0.047524, -4.872295, 0.020000 },
		{ 14.093396, 0.005638, -2.094292, 0.020000 },
		{ 14.093396, 0.000000, -0.281895, 0.020000 },
	};
}

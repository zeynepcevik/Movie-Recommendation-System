
# Movie Recommendation System using Heap-Based Collaborative Filtering    
 
## Project Topic:

This project centers on the development of a movie recommendation system utilizing heap-based collaborative filtering. Collaborative filtering, a prevalent technique in recommendation systems, leverages the preferences of akin users to provide personalized movie recommendations. The heap-based approach implemented here involves employing a heap data structure to efficiently store users most similar to the target user.

## Summary:

The primary objective of this project is to establish a movie recommendation system grounded in the user-movie matrix dataset. By computing similarity scores among users, we discern users with analogous preferences and organize this information within a heap structure. Employing user-specified parameters, denoted as X and K, we generate optimal recommendations and display them on the interface. Additionally, we identify users with comparable preferences and suggest highly-rated movies based on the user's selections. The design of our project incorporates a user-friendly interface, effectively fulfilling our aim of delivering personalized movie recommendations tailored to individual interests.

## Things Done During the Project:

To initiate the project, a dataset in the form of a user-movie matrix was employed, wherein each row represented a user, and each column denoted a movie. The matrix entries indicated user ratings for various movies. Utilizing the cosine similarity metric, we computed the similarity between each pair of users, identified those with similar preferences, and stored this information in a heap. The user-movie matrix dataset was read from a file and managed using a linked list.

Following the computation of similarity scores, a heap was instantiated to retain users with the highest similarity to the target user. Using a MaxHeap structure, we extracted the topmost similar users based on the computed similarity values. An 'X' parameter was obtained from the user, and corresponding to this input, users with ID numbers possessing the closest cosine similarity values to the selected user were printed. Subsequently, a 'K' parameter was solicited from the user, and the 'K' movies that the user favored the most were retrieved. The final output comprised the display of 'X * K' movies in a designated text area.

In the subsequent phase of the project, a new frame was generated. The 'Movies.csv' file was read, and five comboboxes were populated with ten random movies. Users assigned rating values to the selected movies in the comboboxes, and these values were input into adjacent text fields. A vector consisting of 9018 zeros was then created. The movie ID numbers selected in the comboboxes were accessed from the 'Movies.csv' file, and the vector was updated by assigning the rating values to indices corresponding to the respective movie ID numbers.

Finally, the updated vector was traversed in the 'main_data.csv' file to retrieve 'X' and 'K' values. The results were displayed on the screen, providing an integrated overview of user preferences and movie recommendations based on the collaborative filtering approach implemented in the project.

import matplotlib.pyplot as plt
from math import inf
from random import randint
# pygame imports
import pygame
from pygame.locals import *

#constants
maze_len=10
screen_len=900


class Maze():
    def __init__(self, size):
        self.size = size
        self.adjacency_matrix = [[-1 for x in range(size**2)] for y in range(size**2)]
        self.vertc = size * size
        self.connect_adjacent_nodes_in_grid()
        self.P = self.MST_by_prims()
        self.directions = self.parent_array_to_directions()
    def connect_adjacent_nodes_in_grid(self):
        for vertex in range(self.vertc):
            x = vertex % self.size
            y = vertex // self.size
            if x > 0:
                self.adjacency_matrix[vertex][vertex - 1] = randint(1, 50)
            if x < self.size - 1:
                self.adjacency_matrix[vertex][vertex + 1] = randint(1, 50)
            if y > 0:
                self.adjacency_matrix[vertex][vertex - self.size] = randint(1, 50)
            if y < self.size - 1:
                self.adjacency_matrix[vertex][vertex + self.size] = randint(1, 50)
    def plot_mst(self):
        # draw grid of points
        x = [i % self.size for i in range(self.vertc)]
        y = [i // self.size for i in range(self.vertc)]
        plt.scatter(x, y, s=15)
        # draw edges
        for i in range(self.vertc):
            # Draw edge from i to P[i]
            if self.P[i] is not None:
                plt.plot([i % self.size, self.P[i] % self.size], [i // self.size, self.P[i] // self.size], color='r')
            
        plt.show()
        
    def parent_array_to_directions(self):
        directions = []
        for i in range(self.vertc):
            if self.P[i] is not None:
                x = i % self.size
                y = i // self.size
                p_x = self.P[i] % self.size
                p_y = self.P[i] // self.size
                if x == p_x:
                    if y > p_y:
                        directions.append('U')
                        print(f'{self.P[i]} moves up to {i}')
                    else:
                        directions.append('D')
                        print(f'{self.P[i]} moves down to {i}')
                elif y == p_y:
                    if x < p_x:
                        directions.append('L')
                        print(f'{self.P[i]} moves left to {i}')
                    else:
                        directions.append('R')
                        print(f'{self.P[i]} moves right to {i}')
            else:
                directions.append('n')
                print(f'{self.P[i]} does not move to {i}')
                        
        return directions
        
    def plot_mst_animated(self):
        x = [i % self.size for i in range(self.vertc)]
        y = [i // self.size for i in range(self.vertc)]
        plt.scatter(x, y, s=15)
        for i in range(len(self.P)):
            if self.P[i] is not None:
                plt.plot([i % self.size, self.P[i] % self.size], [i // self.size, self.P[i] // self.size], color='r')
                # write direction direct[i] at point
                #plt.text((i % self.size) + 0.5, (i // self.size) + 0.5, direct[i], fontsize=10)
                print(f'{self.P[i]} moves to {i}')
                    
                plt.pause(1)
        plt.show()
        
        
    def plot_by_directions(self):
        x = [i % self.size for i in range(self.vertc)]
        y = [i // self.size for i in range(self.vertc)]
        plt.scatter(x, y, s=15)
        # label each point with its id
        for i in range(self.vertc):
            plt.text((i % self.size) + 0.2, (i // self.size) + 0.2, str(i), fontsize=10)
        for i in range(len(self.P)):
            if self.P[i] is not None:
                if self.directions[i] == 'D':
                    print(f'{self.P[i]} moves down')
                    plt.plot([self.P[i] % self.size, self.P[i] % self.size], [self.P[i] // self.size,self.P[i] // self.size - 1], color='r')
                elif self.directions[i] == 'R':
                    print(f'{self.P[i]} moves right')
                    plt.plot([self.P[i] % self.size, self.P[i] % self.size + 1], [self.P[i] // self.size, self.P[i] // self.size], color='r')
                elif self.directions[i] == 'U':
                    print(f'{self.P[i]} moves up')
                    plt.plot([self.P[i] % self.size, self.P[i] % self.size], [self.P[i] // self.size, self.P[i] // self.size + 1], color='r')
                elif self.directions[i] == 'L':
                    print(f'{self.P[i]} moves left')
                    plt.plot([self.P[i] % self.size, self.P[i] % self.size - 1], [self.P[i] // self.size, self.P[i] // self.size], color='r')
                
            else:
                print('at start')
        plt.show()
            
    def MST_by_prims(self):
        # each node is a vertex, and has ID (size*y + x)
        visited = set()
        P = [None for x in range(self.vertc)]
        # keys represent the vertices, and values represent the known cost of reaching that vertex
        keys = dict.fromkeys(range(self.vertc), inf)
        keys[0] = 0
        # alternatively, while(keys is not empty)
        while (len(visited) < self.vertc):
            # find index of cheapest key
            u = min(keys, key=keys.get)
            # add u to visited nodes
            visited.add(u)
            # remove u from keys
            del keys[u]
            # get neighbors of u
            u_x = u % self.size
            u_y = u // self.size
            neighbors = []
            if u_x > 0:
                neighbors.append(u - 1)
            if u_x < self.size - 1:
                neighbors.append(u + 1)
            if u_y > 0:
                neighbors.append(u - self.size)
            if u_y < self.size - 1:
                neighbors.append(u + self.size)
            # for each neighbor of u
            for v in neighbors:
                # if v is not visited
                if v not in visited:
                    # get weight of edge u-v
                    u_v = self.adjacency_matrix[u][v]
                    # if weight of edge u-v is less than the known cost of reaching v
                    if u_v < keys[v]:
                        # update the known cost of reaching v
                        keys[v] = u_v
                        # update the parent of v so the path to v is through u
                        P[v] = u
        # return the path
        return P
maze = Maze(maze_len)
print(maze.directions)
maze.plot_by_directions()


pass
# # pygame setup
# pygame.init()
# screen = pygame.display.set_mode((screen_len, screen_len))
# pygame.display.set_caption('Maze')
# clock = pygame.time.Clock()

# running = True
# while running:
#     clock.tick(60)
#     for event in pygame.event.get():
#         if event.type == QUIT:
#             running = False
#     screen.fill((0, 0, 0))

    
#     pygame.display.flip()




pass
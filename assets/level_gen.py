
# resulting levels will have over 2^30 tiles by level 2,147,483,642
def methodA(n):
   dimension = (n//2) + 3
   minimum = n//6 + 2
   maximum = n//24*7 + (1,1,2,2,2,2,3,3,3,3,4,4,5,5,5,5,5,5,7,7,7,7,7,7)[n%24]+1
   return (dimension,minimum,maximum)

# resulting levels will have over 2^30 tiles by level 1067
def methodB(n):
   dimension = 253 + int(30.5*n)
   minimum = 65 + int(61*(n+2)/6)
   maximum = 165 + (n-1)//11*196 + (0,18,36,54,72,90,107,125,142,161,179)[(n-1)%11]
   return (dimension,minimum,maximum)

#print methodA(15)

from random import *
def generate(info):
   dim,min,max = info
   matrix = [[ choice(('.','X')) for tile in range(dim)] for row in range(dim)]
   path = ''.join([ choice(('R','D')) for step in range(randint(min,max))])
   #print path
   x = y = pos = 0
   while x < dim and y < dim:
      matrix[y][x] = '.'
      if path[pos % len(path)] == 'R': x +=1
      else: y += 1
      pos += 1
      
   return '\n'.join([''.join(row) for row in matrix])


if __name__ == '__main__':
  setup = methodA(choice((5,10,15,20,25)))
  print setup
  print generate(setup)


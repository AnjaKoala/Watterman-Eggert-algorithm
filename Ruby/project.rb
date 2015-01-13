# Ruby implementation of Smith&Waterman algorithm. 
# Program takes two arguments - paths to files containing strings on which best subsequence alignment needs to be calculated.

require 'matrix'

$MATCHREWARD = 10
$MISMATCH = -9
$INSERTION = -20
$DELETION = -20

# Consumes two strings (row and column) and computes Smith&Waterman 
# matrix. 
def createMatrix(row, column)
    rowLength = row.length
    columnLength = column.length
    matrix = Matrix.build(rowLength + 1, columnLength + 1) {0}
    for r in 1..rowLength
        for c in 1..columnLength
            if row[r - 1] == column[c - 1]
                array = *matrix
                array[r][c] = $MATCHREWARD + matrix[r - 1, c - 1]
                matrix = Matrix[*array]
            else
                array = *matrix
                array[r][c] = [matrix[r, c - 1] + $DELETION, matrix[r - 1, c] + $INSERTION, matrix[r - 1, c - 1] + $MISMATCH, 0].max
                matrix = Matrix[*array]
            end
        end
    end

    return matrix
end

# Returns coordinates of greatest value in given matrix.
def findMax(matrix)
    max = [0, 0]
    for i in 0..matrix.row_size - 1
        for j in 0..matrix.column_size - 1
            if matrix[i, j] > matrix[max[0], max[1]]
                max = [i, j]
            end
        end
    end
    return max
end

# Given coordinates of matrix, returns nearby coordinates with greatest value.
def argMax(choices, matrix)
    max = choices[0]
    for choice in choices
        if matrix[choice[0], choice[1]] > matrix[max[0], max[1]]
            max = choice
        end
    end
    return max
end

# Finds the best path of given matrix (list of coordinates).
def findBestPath(matrix)
    p = findMax(matrix)
    path = []

    dy = [-1, -1, 0]
    dx = [-1, 0, -1]

    choices = [[0, 0], [0, 0], [0, 0]]

    while p[0] > 0 && p[1] > 0 && matrix[p[0], p[1]] != 0
        path.push(p)
        for i in 0..2
            choices[i] = [p[0] + dx[i], p[1] + dy[i]]
        end
        p = argMax(choices, matrix)
    end
    return path
end

# Prints path and corresponding matrix values.
def printPath(path, matrix)
    puts "Printing best path"
    for coordinate in path
        x = coordinate[0]
        y = coordinate[1]
        puts x.to_s + " " + y.to_s + ": " + matrix[x, y].to_s
    end
end

# Prints matrix on the screen, beautifully.
def prettyPrint(matrix) 
    for i in 0..matrix.row_size - 1 
        for j in 0..matrix.column_size - 1
            printf("%3d ", matrix[i, j])
        end
        puts 
    end
end

# Returnes aligned string from given path (list of indices).
def alignString(string, path)
    prev = -1
    alignedString = ""
    for p in path
        if p != prev
            alignedString += string[p - 1]
            prev = p
        else
            alignedString += "-"
        end
    end
    return alignedString
end

# Sets all values at coordinates given by bestPath to zero.
def resetValues(matrix, path)
    for p in path
        array = *matrix
        array[p[0]][p[1]] = 0
        matrix = Matrix[*array]
    end
    return matrix
end

# Recomputes given matrix (Smith&Watterson) into second best matrix (Smith&Eggert).
def recomputeMatrix(matrix, startRow, startColumn, rowString, columnString)
    for r in startRow..matrix.row_size - 1
        for c in startColumn..matrix.column_size - 1
            if matrix[r, c] > 0
                if rowString[r - 1] == columnString[c - 1]
                    array = *matrix
                    array[r][c] = $MATCHREWARD + matrix[r - 1, c - 1]
                    matrix = Matrix[*array]
                else
                    array = *matrix
                    array[r][c] = [matrix[r, c - 1] + $DELETION, matrix[r - 1, c] + $INSERTION, matrix[r - 1, c - 1] + $MISMATCH, 0].max
                    matrix = Matrix[*array]
                end
            end
        end
    end
    return matrix
end

def printAligned(path, row, column)
    rowPath = []
    columnPath = []
    path.each { |p| rowPath.push(p[0]) and columnPath.push(p[1]) }

    puts alignString(row, rowPath)
    puts alignString(column, columnPath)
end

puts "PROGRAM START"
puts "---------------------------------------------"

column = IO.readlines(ARGV[0])[0]
row = IO.readlines(ARGV[1])[0]

column = column[0...-1] # removing last space
row = row[0...-1] # removing last space

matrix = createMatrix(row, column)
prettyPrint(matrix)

bestPath = findBestPath(matrix)

printPath(bestPath, matrix)

# Resets values in best path to zero.
matrix = resetValues(matrix, bestPath)

start = bestPath[-1]
matrix = recomputeMatrix(matrix, start[0], start[1], row, column)
secondBestPath = findBestPath(matrix)

printPath(secondBestPath, matrix)
prettyPrint(matrix)

# Printing aligned substring from the first matrix.
printAligned(bestPath, row, column)

# Printing aligned substring from the second matrix.
printAligned(secondBestPath, row, column)

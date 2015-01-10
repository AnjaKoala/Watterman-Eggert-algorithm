using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace WattermanEggert
{
    /**
     * Implementation of Watterman-Eggert Algorithm for second best string alignment
     * 
     * The program takes three inputs:
     *    first string to be aligned
     *    second string to be aligned
     *    debug value: if third argument is DEBUG, the program will output H and H* matrices with the matching best pathts through them
     **/ 
    class Program
    {
        /* Amount of hate we put into mismatch :) */
        private static int _matchReward = 10;
        private static int _mismatchCost = -9;
        private static int _insertionCost = -20;
        private static int _deletionCost = -20;

        static void Main(string[] args)
        {
            string row = args[0];
            string column = args[1];

            /** Should the debug info be printed? */
            bool debug = args.Length > 2 && args[3].ToLower() == "debug";
            
            /** Uses two input strings to create the Smith&Waterman matrix based on the costs and rewards defined earlier. */
            int[,] matrix = CreateMatrix(row, column);
            if (debug) PrintMatrix(matrix);

            /** Store the bast path as list of int pairs which represent the coordinates within the matrix. */
            var bestPath = FindBestPath(matrix);

            if (debug) PrintPath(bestPath, matrix);
            /** Sets all matrix' values along the path to zero hence computing the H* matrix. (Asterisks stands for "slightly modified"). */
            ResetValues(matrix, bestPath);
            if (debug) PrintMatrix(matrix);

            /** Uses the H* matrix as basis for calculating the new "best" path. */
            var secondBestPath = FindBestPath(matrix);
            if (debug)
            {
                PrintPath(secondBestPath, matrix);
                Console.WriteLine(AlignString(column, bestPath.Select(t => t.Item1).ToList()));
                Console.WriteLine(AlignString(row, bestPath.Select(t => t.Item2).ToList()));
            }

            /** Align column nucleotides based on the first coordinates of path pairs. */
            string rowAligned = AlignString(column, secondBestPath.Select(t => t.Item1).ToList());
            Console.WriteLine(rowAligned);

            /** Align row nucleotides based on the first coordinates of path pairs. */
            string columnAligned = AlignString(row, secondBestPath.Select(t => t.Item2).ToList());
            Console.WriteLine(columnAligned);
        }

        /** Computes the Smith&Waterman matrix used for string comparison. Also adds one null row and one null column for the sake of implementation simplicity. */
        static int[,] CreateMatrix(string rowString, string columnString)
        {
            var matrix = new int[columnString.Length + 1, rowString.Length + 1];

            for (int r = 1; r <= rowString.Length; r++)
            {
                for (int c = 1; c <= columnString.Length; c++)
                {
                    if (columnString[r - 1] == rowString[c - 1])
                    {
                        matrix[r, c] = _matchReward + matrix[r - 1, c - 1];
                    }
                    else
                    {
                        matrix[r, c] = Max4(matrix[r, c - 1] + _deletionCost, matrix[r - 1, c] + _insertionCost, matrix[r - 1, c - 1] + _mismatchCost, 0);
                    }
                }
            }

            return matrix;
        }

        static int Max4(int a, int b, int c, int d)
        {
            return Math.Max(Math.Max(a, b), Math.Max(c, d));
        }

        static void PrintMatrix(int[,] matrix)
        {
            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    Console.Write("{0,4}", matrix[i, j]);
                    
                }
                Console.WriteLine();
            }
        }

        /** Returns the coordinates of greatest number within a integer matrix. */
        static Tuple<int, int> FindMax(int[,] matrix)
        {
            var max = new Tuple<int, int>(0, 0);

            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    if (matrix[i, j] > matrix[max.Item1, max.Item2])
                    {
                        max = new Tuple<int, int>(i, j);
                    }
                }
            }

            return max;
        }

        /** Takes array of int pairs which represent coordinates within the matrix. Returns the point which points to greatest number within the matrix. */
        static Tuple<int, int> ArgMax(Tuple<int, int>[] choices, int[,] matrix)
        {
            Tuple<int, int> max = choices[0];
            foreach (var choice in choices)
            {
                if (matrix[choice.Item1, choice.Item2] >= matrix[max.Item1, max.Item2])
                {
                    max = choice;
                }
            }

            return max;
        }

        /** After finding the coordinates of the greatest matrix' element, the best path is computed. 
         *  The best path is computed by repeatedly taking the greatest element of the three
         *  in the upper-left corner of the starting one.
         *  For further references, see Smith&Waterson algorithm. 
         **/
        static List<Tuple<int, int>> FindBestPath(int[,] matrix)
        {
            var p = FindMax(matrix);
            var path = new List<Tuple<int, int>> {p};

            int[] dy = { -1, -1, 0 };
            int[] dx = { 0, -1, -1 };
            
            var choices = new Tuple<int, int>[3];
            while (true)
            {
                for (int i = 0; i < 3; i++)
                {
                    choices[i] = new Tuple<int, int>(p.Item1 + dx[i], p.Item2 + dy[i]);
                }
                p = ArgMax(choices, matrix);
                if (matrix[p.Item1, p.Item2] == 0)
                {
                    break;
                }
                path.Add(p);
            }
            
            return path;
        }

        /** All values in matrix at coordinates indicated by elements of path are set to zero. */
        static void ResetValues(int[,] matrix, List<Tuple<int, int>>  path)
        {
            foreach (var p in path)
            {
                matrix[p.Item1, p.Item2] = 0;
            }
        }

        /** Rearanges the string by using the indices in path.
         *  Indices must be sorted in descending order.
         *  If indices are repeated, a single dash character - is
         *  inserted for every repetition.
         **/
        static string AlignString(string a, List<int> path)
        {
            int prev = -1;
            StringBuilder sb = new StringBuilder();
            foreach (int p in path)
            {
                if (p != prev)
                {
                    sb.Append(a[p - 1]);
                    prev = p;
                }
                else
                {
                    sb.Append('-');
                }
            }
            var stringAsCharArray = sb.ToString().ToCharArray();
            Array.Reverse(stringAsCharArray);
            return new string(stringAsCharArray);
        }

        /** Prints values of matrix along the given path. */
        static void PrintPath(List<Tuple<int, int>> path, int[,] matrix)
        {
            Console.WriteLine(string.Join(", ", path.Select(p => matrix[p.Item1, p.Item2])));
        }
    }
}

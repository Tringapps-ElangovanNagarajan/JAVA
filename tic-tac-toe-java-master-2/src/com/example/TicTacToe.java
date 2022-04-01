package com.example;

public class TicTacToe {
          public static Evaluation evaluateBoard(String board) {
                  board = board.toLowerCase();
                  if(board.length() != 9) {
                          return Evaluation.InvalidInput;
                  }
                  int xCount = 0;
                  int oCount = 0;
                  for(int i = 0; i < board.length(); i++) {
                          if(board.charAt(i) == 'x') {
                                  xCount++;
                          }
                          if(board.charAt(i) == 'o') {
                                  oCount++;
                          }
                  }
                  if(Math.abs(xCount - oCount) >= 2) {
                          return Evaluation.UnreachableState;
                  }
                  boolean xWins = checkWinner('x', board);
                  boolean oWins = checkWinner('o', board);
                  if(xWins && oWins) {
                          return Evaluation.UnreachableState;
                  }
                  if(xWins) {
                          return Evaluation.Xwins;
                  }
                  else if(oWins) {
                          return Evaluation.Owins;
                  }
                  else {
                          return Evaluation.NoWinner;
                  }
          }
          
          public static boolean checkWinner(char check, String board) {
                  if(board.charAt(0) == check && board.charAt(1) == check && board.charAt(2) == check) {
                          return true;
                  }
                  if(board.charAt(3) == check && board.charAt(4) == check && board.charAt(5) == check) {
                          return true;
                  }
                  if(board.charAt(6) == check && board.charAt(7) == check && board.charAt(8) == check) {
                          return true;
                  }
                  if(board.charAt(0) == check && board.charAt(3) == check && board.charAt(6) == check) {
                          return true;
                  }
                  if(board.charAt(1) == check && board.charAt(4) == check && board.charAt(7) == check) {
                          return true;
                  }
                  if(board.charAt(2) == check && board.charAt(5) == check && board.charAt(8) == check) {
                          return true;
                  }
                  if(board.charAt(0) == check && board.charAt(4) == check && board.charAt(8) == check) {
                          return true;
                  }
                  return board.charAt(2) == check && board.charAt(4) == check && board.charAt(6) == check;
          }
}
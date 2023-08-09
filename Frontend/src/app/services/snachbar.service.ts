import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnachbarService {

  constructor(private _snackBar: MatSnackBar) { }

  openSnackBar(message: string, action: string) {
    if (action === 'error') {
      this._snackBar.open(message, '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 4000,
        panelClass: ['green-snackbar']
      });
    }
    else {
      this._snackBar.open(message, '', {
        horizontalPosition: 'center',
        verticalPosition: 'top',
        duration: 4000,
        panelClass: ['green-snackbar']
      });
    }
  }
}

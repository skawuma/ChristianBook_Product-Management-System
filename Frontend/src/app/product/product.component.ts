import { Component } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { ProductService } from '../services/product.service';
import { SnachbarService } from '../services/snachbar.service';
import { GlobalConstants } from '../shared/global-constants';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {
  search: any;
  response: any;
  hideOrShow: any = true;
  responseMessage: any;

  constructor(private ngxService: NgxUiLoaderService,
    private productService: ProductService,
    private snachbarService: SnachbarService) { }

  onSubmit() {
    this.ngxService.start();
    this.productService.searchProduct(this.search).subscribe((response: any) => {
      this.ngxService.stop();
      this.response = response;
      this.hideOrShow = false;
      console.log(response);
    }, (error) => {
      this.hideOrShow = true;
      console.log(error);
      this.ngxService.stop();
      if (error.error?.message) {
        this.responseMessage = error.error?.message;
      }
      else {
        this.responseMessage = GlobalConstants.genericError;
      }
      this.snachbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    });
  }
}

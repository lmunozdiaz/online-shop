import { __decorate } from "tslib";
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// material dependencies
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
let MaterialModule = class MaterialModule {
};
MaterialModule = __decorate([
    NgModule({
        declarations: [],
        imports: [
            CommonModule,
            MatIconModule,
            MatButtonModule,
            MatToolbarModule,
            MatSidenavModule,
            MatListModule,
        ],
        exports: [
            MatIconModule,
            MatButtonModule,
            MatToolbarModule,
            MatSidenavModule,
            MatListModule,
        ]
    })
], MaterialModule);
export { MaterialModule };
//# sourceMappingURL=material.module.js.map
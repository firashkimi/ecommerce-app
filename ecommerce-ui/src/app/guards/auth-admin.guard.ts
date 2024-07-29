import { CanActivateFn, Router } from '@angular/router';
import { SessionStorageService } from '../services/session-storage.service';
import { inject } from '@angular/core';

export const authAdminGuard: CanActivateFn = (route, state) => {
  const sesion= inject(SessionStorageService);
  const router= inject(Router);

  if(sesion.getItem('userData') != null){
    if(sesion.getItem('userData')?.type === 'ADMIN'){
      return true;
    } else {
      return router.createUrlTree(['']);
    }
  }

  return router.createUrlTree(['/user/login']);
};

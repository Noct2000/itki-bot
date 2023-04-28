import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MenuComponent } from './menu.component';
import { QuestionsComponent } from '../questions/questions.component';
import { AuthGuard } from '../../auth/auth.guard';
import { CuratorsComponent } from '../curators/curators.component';
import { SendMessageComponent } from '../send-message/send-message.component';

const routes: Routes = [
  {
    path: '',
    component: MenuComponent,
    children: [
      {
        path: 'questions',
        component: QuestionsComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'curators',
        component: CuratorsComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'telegram-users',
        loadChildren: () => import('../telegram-users/telegram-users.module').then(m => m.TelegramUsersModule),
        canActivate: [AuthGuard],
      },
      {
        path: 'send-message',
        component: SendMessageComponent,
        canActivate: [AuthGuard],
      },
    ],
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuRoutingModule { }

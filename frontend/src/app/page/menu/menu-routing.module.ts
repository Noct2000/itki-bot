import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MenuComponent } from './menu.component';
import { AuthGuard } from '../../auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: MenuComponent,
    children: [
      {
        path: 'questions',
        loadChildren: () => import('../questions/questions.module').then(m => m.QuestionsModule),
        canActivate: [AuthGuard],
      },
      {
        path: 'curators',
        loadChildren: () => import('../curators/curators.module').then(m => m.CuratorsModule),
        canActivate: [AuthGuard],
      },
      {
        path: 'telegram-users',
        loadChildren: () => import('../telegram-users/telegram-users.module').then(m => m.TelegramUsersModule),
        canActivate: [AuthGuard],
      },
      {
        path: 'send-message',
        loadChildren: () => import('../send-message/send-message.module').then(m => m.SendMessageModule),
        canActivate: [AuthGuard],
      },
      {
        path: 'groups',
        loadChildren: () => import('../groups/groups.module').then(m => m.GroupsModule),
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
